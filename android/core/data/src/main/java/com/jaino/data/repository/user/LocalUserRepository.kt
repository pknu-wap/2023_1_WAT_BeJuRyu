package com.jaino.data.repository.user

import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {

    fun getNickname() : Flow<String>

    fun getUserId() : Flow<Long>

    fun clear()
}