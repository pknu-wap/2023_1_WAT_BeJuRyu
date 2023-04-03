package com.jaino.datastore

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val FILE_NAME = "BEJURYU_DATA"

class BeJuRyuDatastoreImpl @Inject constructor(
    @ApplicationContext context: Context
) : BeJuRyuDatastore{

    private val storeDelegate by lazy {
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
        set(value) = storeDelegate.edit { putString("REFRESH_TOKEN", value) }
        get() = storeDelegate.getString("REFRESH_TOKEN", "") ?: ""

    override var refreshToken: String
        set(value) = storeDelegate.edit { putString("REFRESH_TOKEN", value) }
        get() = storeDelegate.getString("REFRESH_TOKEN", "") ?: ""

    override var userId: Int
        set(value) = storeDelegate.edit { putInt("USER_ID", value) }
        get() = storeDelegate.getInt("USER_ID", -1)

    override fun clear() {
        storeDelegate.edit { clear() }
    }
}
