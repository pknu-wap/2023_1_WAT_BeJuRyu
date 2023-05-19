package com.jaino.network.remote

import com.jaino.network.model.response.base.BaseResponse
import com.jaino.network.model.response.base.EmptyResponse
import com.jaino.network.model.request.review.WriteDrinkReviewRequest
import com.jaino.network.model.response.review.DrinkReviewResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ReviewService {

    @GET("/drinks/{drink-id}")
    suspend fun getReviewList(
        @Query("drink-id") drink_id : Long
    ): BaseResponse<List<DrinkReviewResponse>>

    @POST("/drinks/{drink-id}/review")
    suspend fun postReview(
        @Query("drink-id") drink_id : Long,
        @Body writeDrinkReviewRequest : WriteDrinkReviewRequest
    ): EmptyResponse

}
