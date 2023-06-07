package com.jaino.data.repository.user

interface LocalUserRepository {

    suspend fun setNickname(nickname: String)

    suspend fun getNickName() : String

    suspend fun getUserId() : Long

    fun clear()
}