package com.jaino.network.remote

import com.jaino.network.model.response.rank.RankListResponse
import retrofit2.http.GET

interface RankService {
    @GET("/drinks/rankings/rating")
    suspend fun getHighestRatedList(): RankListResponse

    @GET("/drinks/rankings/review")
    suspend fun getMostReviewedList(): RankListResponse
}