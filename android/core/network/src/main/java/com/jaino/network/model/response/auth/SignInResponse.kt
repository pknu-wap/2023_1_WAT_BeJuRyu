package com.jaino.network.model.response.auth

import com.jaino.model.setting.Profile
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse (
    val memberResponse : MemberResponse,
    val token : TokenResponse
)

@Serializable
data class MemberResponse(
    val id : Long,
    val nickname : String
){
    fun toProfile(): Profile = Profile(
        userId = id,
        nickname = nickname
    )
}

@Serializable
data class TokenResponse(
    val access: String,
    val refresh: String
)