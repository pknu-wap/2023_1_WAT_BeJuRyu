package com.jaino.datastore

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val FILE_NAME = "BEJURYU_DATA"

class TokenDataSourceImpl @Inject constructor(
    @ApplicationContext context: Context
) : TokenDataSource{

    private val tokenStore by lazy {
        if (BuildConfig.DEBUG) context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        else EncryptedSharedPreferences.create(
            FILE_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override var accessToken: String
        set(value) = tokenStore.edit { putString("ACCESS_TOKEN", value) }
        get() = tokenStore.getString("ACCESS_TOKEN", "") ?: ""

    override var refreshToken: String
        set(value) = tokenStore.edit { putString("REFRESH_TOKEN", value) }
        get() = tokenStore.getString("REFRESH_TOKEN", "") ?: ""

    override fun clearToken() {
        tokenStore.edit { clear() }
    }
}
