package com.jaino.network.model.response.auth

import com.jaino.model.auth.SignIn
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse (
    val memberResponse : MemberResponse,
    val token : TokenResponse
){
    fun toSignIn(): SignIn = SignIn(
        refreshToken = token.refresh,
        accessToken = token.access,
        userId = memberResponse.id,
        nickName = memberResponse.nickname
    )
}

@Serializable
data class MemberResponse(
    val id : Long,
    val nickname : String
)

@Serializable
data class TokenResponse(
    val access: String,
    val refresh: String
)