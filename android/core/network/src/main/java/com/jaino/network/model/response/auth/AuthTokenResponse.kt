package com.jaino.network.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenResponse (
    val access: String,
    val refresh: String
)