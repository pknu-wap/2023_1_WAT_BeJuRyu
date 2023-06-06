package com.jaino.network.remote

import com.jaino.network.model.response.base.EmptyResponse
import com.jaino.network.model.request.review.WriteDrinkReviewRequest
import com.jaino.network.model.response.review.DrinkReviewListResponse
import retrofit2.http.*


interface ReviewService {

    @GET("/drinks/{drink-id}/reviews")
    suspend fun getReviewList(
        @Path("drink-id") drink_id : Long
    ): DrinkReviewListResponse

    @POST("/drinks/{drink-id}/reviews")
    suspend fun postReview(
        @Path("drink-id") drink_id : Long,
        @Body writeDrinkReviewRequest : WriteDrinkReviewRequest
    )

}
