package com.jaino.data.repository.setting

import com.jaino.model.setting.Profile
import com.jaino.network.datasource.setting.ProfileDataSource
import com.jaino.network.model.request.setting.ProfileRequest
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
): ProfileRepository{
    override suspend fun getProfile(): Result<Profile> {
        return dataSource.getProfile().mapCatching { it.toProfile() }
    }

    override suspend fun editNickname(userId: Long, nickname: String): Result<Profile> {
        return dataSource.editNickname(ProfileRequest(userId, nickname)).mapCatching {
            it.toProfile()
        }
    }
}