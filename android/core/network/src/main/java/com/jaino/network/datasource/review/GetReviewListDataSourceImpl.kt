package com.jaino.network.datasource.review

import com.jaino.network.model.response.review.DrinkReviewResponse
import com.jaino.network.remote.ReviewService
import javax.inject.Inject

class GetReviewListDataSourceImpl @Inject constructor(
    private val service : ReviewService
): GetReviewListDataSource{

    override suspend fun getReviewList(drinkId : Long): Result<List<DrinkReviewResponse>> =
        runCatching {
            service.getReviewList(drinkId).data
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkReviewResponse>>(error)
        }

}