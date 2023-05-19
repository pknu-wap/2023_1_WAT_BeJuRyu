package com.jaino.data.repository.user

interface LocalUserRepository {
    suspend fun getNickName() : String

    suspend fun getUserId() : Long

    fun clear()
}