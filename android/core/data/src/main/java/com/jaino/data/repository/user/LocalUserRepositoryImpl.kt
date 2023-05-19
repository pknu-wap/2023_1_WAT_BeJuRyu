package com.jaino.data.repository.user

import com.jaino.datastore.BeJuRyuDatastore
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val dataStore : BeJuRyuDatastore
) : LocalUserRepository{
    override suspend fun getNickName(): String = dataStore.nickName

    override suspend fun getUserId(): Long = dataStore.userId

    override fun clear() = dataStore.clear()
}