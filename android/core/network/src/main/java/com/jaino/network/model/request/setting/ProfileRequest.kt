package com.jaino.network.model.request.setting

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    val userId : Long,
    val newNickname : String
)