package com.jaino.data.repository.auth

interface AuthRepository {
    suspend fun signInService(token: String) : Result<Unit>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)

}