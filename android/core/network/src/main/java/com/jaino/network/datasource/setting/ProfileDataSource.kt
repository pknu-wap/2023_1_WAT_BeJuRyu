package com.jaino.network.datasource.setting

import com.jaino.network.model.request.setting.ProfileRequest
import com.jaino.network.model.response.auth.MemberResponse

interface ProfileDataSource {

    suspend fun getProfile(): Result<MemberResponse>

    suspend fun editNickname(profileRequest: ProfileRequest): Result<MemberResponse>

}