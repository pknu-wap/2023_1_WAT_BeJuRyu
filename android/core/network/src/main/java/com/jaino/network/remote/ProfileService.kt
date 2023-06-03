package com.jaino.network.remote

import com.jaino.network.model.request.setting.ProfileRequest
import com.jaino.network.model.response.auth.MemberResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileService {

    @GET("/member/{user_id}")
    suspend fun getProfile(
        @Path("user_id") userId: Long
    ): MemberResponse

    @PUT("/member/nickname")
    suspend fun editNickname(
        @Body profileRequest : ProfileRequest
    ): MemberResponse
}