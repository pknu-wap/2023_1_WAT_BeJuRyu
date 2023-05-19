package com.jaino.data.repository.review

import com.jaino.model.review.DrinkReview
import com.jaino.model.review.WriteReview

interface ReviewRepository {
    suspend fun getReviewList(drinkId : Long): Result<List<DrinkReview>>

    suspend fun postReview(drinkId: Long, review : WriteReview) : Result<Unit>
}