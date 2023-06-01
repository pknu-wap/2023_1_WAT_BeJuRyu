package com.jaino.data.repository.review

import com.jaino.data.model.review.ReviewRequest
import com.jaino.model.review.Review

interface ReviewRepository {
    suspend fun getReviewList(drinkId : Long): Result<List<Review>>

    suspend fun postReview(drinkId: Long, review : ReviewRequest) : Result<Unit>
}