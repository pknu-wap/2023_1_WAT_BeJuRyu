package com.jaino.data.repository.auth

import com.jaino.datastore.TokenDataSource
import com.jaino.datastore.UserPreferencesDataSource
import com.jaino.network.datasource.auth.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val source: AuthDataSource,
    private val datastore: TokenDataSource,
    private val userPreferencesDataSource: UserPreferencesDataSource
) : AuthRepository {
    override suspend fun signInService(token: String) : Result<Unit> {
        runCatching { source.signIn(token) }
            .onSuccess {
                setAccessToken(it.token.access)
                setRefreshToken(it.token.refresh)
                userPreferencesDataSource.setUserId(it.memberResponse.id)
                userPreferencesDataSource.setNickname(it.memberResponse.nickname)
                return Result.success(Unit)
            }
            .onFailure{
                return Result.failure(it)
            }
        return Result.failure(Throwable("AuthRepositoryImpl UnKnown error"))
    }

    override fun setAccessToken(token: String) {
        datastore.accessToken = token
    }

    override fun setRefreshToken(token: String) {
        datastore.refreshToken = token
    }
}