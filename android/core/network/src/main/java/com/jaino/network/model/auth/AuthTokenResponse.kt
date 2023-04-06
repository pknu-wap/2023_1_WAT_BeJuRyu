package com.jaino.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenResponse (
    val accessToken: String,
    val refreshToken: String
)