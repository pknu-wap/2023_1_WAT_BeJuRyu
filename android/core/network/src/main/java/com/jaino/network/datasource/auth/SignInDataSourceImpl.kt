package com.jaino.network.datasource.auth

import com.jaino.network.model.auth.SignInResponse
import com.jaino.network.remote.AuthService
import javax.inject.Inject

class SignInDataSourceImpl @Inject constructor(
    private val service : AuthService
) : SignInDataSource{
    override suspend fun signIn(token: String) : SignInResponse =
        service.signIn(token)
}