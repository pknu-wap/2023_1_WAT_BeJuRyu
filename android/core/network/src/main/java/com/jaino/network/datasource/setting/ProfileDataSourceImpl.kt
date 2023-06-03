package com.jaino.network.datasource.setting

import com.jaino.network.model.request.setting.ProfileRequest
import com.jaino.network.model.response.auth.MemberResponse
import com.jaino.network.remote.ProfileService
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val service : ProfileService
): ProfileDataSource{

    override suspend fun getProfile(userId: Long): Result<MemberResponse> =
        runCatching {
            service.getProfile(userId)
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<MemberResponse>(error)
        }

    override suspend fun editNickname(profileRequest: ProfileRequest): Result<MemberResponse> =
        runCatching {
            service.editNickname(profileRequest)
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<MemberResponse>(error)
        }
}