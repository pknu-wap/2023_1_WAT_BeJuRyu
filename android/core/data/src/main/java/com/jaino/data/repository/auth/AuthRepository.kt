package com.jaino.data.repository.auth

interface AuthRepository {
    suspend fun signInService(token: String)

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)

    fun setNickName(nickName: String)

    fun setUserId(userId: Int)
}