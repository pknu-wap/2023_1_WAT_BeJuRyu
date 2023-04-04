package com.jaino.network.model.auth

import com.jaino.model.auth.SignIn

data class SignInResponse (
    val accessToken: String,
    val refreshToken : String,
    val useId : Int,
    val nickName : String
    ){
    fun toSignIn(): SignIn = SignIn(
        accessToken = accessToken,
        refreshToken = refreshToken,
        useId = useId,
        nickName = nickName
    )
}