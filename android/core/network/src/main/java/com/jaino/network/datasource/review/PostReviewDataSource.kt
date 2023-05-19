package com.jaino.network.datasource.review

import com.jaino.network.model.request.review.WriteDrinkReviewRequest

interface PostReviewDataSource {
    suspend fun postReview(drinkId: Long, request : WriteDrinkReviewRequest): Result<Unit>
}