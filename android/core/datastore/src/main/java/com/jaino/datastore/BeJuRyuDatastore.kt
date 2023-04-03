package com.jaino.datastore

interface BeJuRyuDatastore {
    var accessToken: String
    var refreshToken: String
    var userId: Int

    fun clear()
}
