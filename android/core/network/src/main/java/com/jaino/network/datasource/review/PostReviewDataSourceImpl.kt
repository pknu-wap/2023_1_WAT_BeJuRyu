package com.jaino.network.datasource.review

import com.jaino.network.model.request.review.WriteDrinkReviewRequest
import com.jaino.network.remote.ReviewService
import javax.inject.Inject

class PostReviewDataSourceImpl @Inject constructor(
    private val service : ReviewService
): PostReviewDataSource{
    override suspend fun postReview(drinkId: Long, request: WriteDrinkReviewRequest): Result<Unit> =
        runCatching {
            service.postReview(drinkId, request)
            return Result.success(Unit)
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<Unit>(error)
        }
}