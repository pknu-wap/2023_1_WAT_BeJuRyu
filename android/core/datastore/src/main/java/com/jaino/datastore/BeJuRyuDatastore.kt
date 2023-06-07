package com.jaino.datastore

interface BeJuRyuDatastore {
    var accessToken: String
    var refreshToken: String
    var nickName : String
    var userId: Long

    fun clear()
}
