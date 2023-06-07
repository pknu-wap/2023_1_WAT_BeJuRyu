package com.jaino.model.auth

data class User(
    val accessToken: String,
    val refreshToken : String,
    val userId : Long,
    val nickName : String
)