package com.jaino.network.model.response.base

@kotlinx.serialization.Serializable
data class EmptyResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
)