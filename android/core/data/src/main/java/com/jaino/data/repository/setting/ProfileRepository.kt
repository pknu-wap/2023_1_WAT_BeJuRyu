package com.jaino.data.repository.setting

import com.jaino.model.setting.Profile

interface ProfileRepository {
    suspend fun getProfile(): Result<Profile>

    suspend fun editNickname(userId: Long, nickname: String): Result<Profile>
}