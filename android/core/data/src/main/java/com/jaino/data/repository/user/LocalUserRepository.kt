package com.jaino.data.repository.user

interface LocalUserRepository {
    suspend fun getNickName() : String
}