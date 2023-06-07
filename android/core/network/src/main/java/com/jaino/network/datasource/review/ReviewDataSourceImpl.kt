package com.jaino.network.datasource.review

import com.jaino.network.model.request.review.WriteDrinkReviewRequest
import com.jaino.network.model.response.review.DrinkReviewResponse
import com.jaino.network.remote.ReviewService
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val service : ReviewService
): ReviewDataSource{
    override suspend fun postReview(drinkId: Long, request: WriteDrinkReviewRequest): Result<Unit> =
        runCatching {
            service.postReview(drinkId, request)
            return Result.success(Unit)
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<Unit>(error)
        }

    override suspend fun getReviewList(drinkId : Long): Result<List<DrinkReviewResponse>> =
        runCatching {
            service.getReviewList(drinkId).reviews
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkReviewResponse>>(error)
        }

}