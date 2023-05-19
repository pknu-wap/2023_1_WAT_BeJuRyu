package com.jaino.network.datasource.auth

import com.jaino.network.model.response.auth.SignInResponse


interface SignInDataSource {
    suspend fun signIn(token: String) : SignInResponse
}