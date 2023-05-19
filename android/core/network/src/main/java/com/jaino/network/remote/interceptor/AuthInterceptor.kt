package com.jaino.network.remote.interceptor

import android.content.Context
import com.jaino.datastore.BeJuRyuDatastore
import com.jaino.network.BuildConfig
import com.jaino.network.model.response.auth.AuthTokenResponse
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStore: BeJuRyuDatastore,
    private val json: Json,
    @ApplicationContext private val context: Context
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer ${dataStore.accessToken}")
                .build()
        val response = chain.proceed(authRequest)
        when (response.code) {
            // 액세스 토큰 만료
            401 -> {
                try {
                    val refreshTokenRequest = originalRequest.newBuilder().get()
                        .url("${BuildConfig.BASE_DOMAIN_URL}/auth/refresh")
                        .addHeader("accessToken", dataStore.accessToken)
                        .addHeader("refreshToken", dataStore.refreshToken)
                        .build()
                    val refreshTokenResponse = chain.proceed(refreshTokenRequest)

                    if (refreshTokenResponse.isSuccessful) {
                        val responseToken: AuthTokenResponse =
                            json.decodeFromString(
                                requireNotNull(refreshTokenResponse.body?.string())
                            )
                        with(dataStore) {
                            accessToken = responseToken.access
                            refreshToken = responseToken.refresh
                        }
                        refreshTokenResponse.close()
                        val newRequest = originalRequest.newBuilder()
                            .addHeader("Authorization", "Bearer ${dataStore.accessToken}")
                            .build()
                        return chain.proceed(newRequest)
                    } else {
                        dataStore.accessToken = ""
                        dataStore.refreshToken = ""
                    }
                } catch (e: Throwable) {
                    Timber.e(e)
                    dataStore.accessToken = ""
                    dataStore.refreshToken = ""
                    ProcessPhoenix.triggerRebirth(context)
                }
            }
        }
        return response
    }
}