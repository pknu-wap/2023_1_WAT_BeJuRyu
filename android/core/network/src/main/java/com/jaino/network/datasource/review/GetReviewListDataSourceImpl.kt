package com.jaino.network.datasource.review

import com.jaino.network.model.response.review.DrinkReviewResponse
import com.jaino.network.remote.ReviewService
import javax.inject.Inject

class GetReviewListDataSourceImpl @Inject constructor(
    private val service : ReviewService
): GetReviewListDataSource{

    override suspend fun getReviewList(drinkId : Long): Result<List<DrinkReviewResponse>> =
        runCatching {
            // service.getReviewList(drinkId).data
            listOf(
                DrinkReviewResponse(
                reviewId = 23,
                nickname = "jaino",
                review = "존맛 탱구리",
                score = 3
                ),
                DrinkReviewResponse(
                    reviewId = 24,
                    nickname = "황재현인데요?",
                    review = "개쩐다",
                    score = 5
                ),
                DrinkReviewResponse(
                    reviewId = 23,
                    nickname = "림현도?",
                    review = "늘 먹던 맛",
                    score = 1
                )
            )
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkReviewResponse>>(error)
        }

}