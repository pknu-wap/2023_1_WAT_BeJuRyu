package com.jaino.network.datasource.auth

import com.jaino.network.model.response.auth.SignInResponse
import com.jaino.network.remote.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val service : AuthService
) : AuthDataSource{
    override suspend fun signIn(token: String) : SignInResponse =
        service.signIn(token)
}