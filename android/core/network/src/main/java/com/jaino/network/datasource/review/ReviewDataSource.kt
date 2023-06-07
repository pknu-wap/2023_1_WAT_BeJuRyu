package com.jaino.network.datasource.review

import com.jaino.network.model.request.review.WriteDrinkReviewRequest
import com.jaino.network.model.response.review.DrinkReviewResponse

interface ReviewDataSource {

    suspend fun getReviewList(drinkId: Long) : Result<List<DrinkReviewResponse>>
    suspend fun postReview(drinkId: Long, request : WriteDrinkReviewRequest): Result<Unit>

}