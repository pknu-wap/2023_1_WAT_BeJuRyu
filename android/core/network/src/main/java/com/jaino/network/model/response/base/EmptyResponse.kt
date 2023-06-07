package com.jaino.network.model.response.base

import kotlinx.serialization.Serializable

@Serializable
data class EmptyResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
)