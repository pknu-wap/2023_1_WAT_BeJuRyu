package com.jaino.network.remote

import com.jaino.network.model.auth.SignInResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {

    @GET("/auth/login")
    suspend fun signIn(
        @Query("token") token: String
    ) : SignInResponse
}