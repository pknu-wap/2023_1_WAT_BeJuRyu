package com.jaino.network.datasource.auth

import com.jaino.network.model.response.auth.SignInResponse


interface AuthDataSource {
    suspend fun signIn(token: String) : SignInResponse
}