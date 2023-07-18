package com.jaino.datastore

interface TokenDataSource {
    var refreshToken: String
    var accessToken: String

    fun clearToken()
}
