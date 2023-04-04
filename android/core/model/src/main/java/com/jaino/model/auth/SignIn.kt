package com.jaino.model.auth

data class SignIn(
    val accessToken: String,
    val refreshToken : String,
    val useId : Int,
    val nickName : String
)