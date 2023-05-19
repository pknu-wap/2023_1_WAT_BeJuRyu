package com.jaino.model.auth

data class SignIn(
    val accessToken: String,
    val refreshToken : String,
    val userId : Long,
    val nickName : String
)