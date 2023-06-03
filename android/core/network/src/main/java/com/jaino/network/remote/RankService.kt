package com.jaino.network.remote

import com.jaino.network.model.response.dictionary.DrinkListResponse
import retrofit2.http.GET

interface RankService {
    @GET("/drinks/rankings/rating")
    suspend fun getHighestRatedList(): DrinkListResponse

    @GET("/drinks/rankings/review")
    suspend fun getMostReviewedList(): DrinkListResponse
}