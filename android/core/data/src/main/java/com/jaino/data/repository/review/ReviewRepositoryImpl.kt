package com.jaino.data.repository.review

import com.jaino.data.model.review.ReviewRequest
import com.jaino.model.review.Review
import com.jaino.network.datasource.review.ReviewDataSource
import com.jaino.network.model.request.review.WriteDrinkReviewRequest
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewDataSource: ReviewDataSource
) : ReviewRepository{
    override suspend fun getReviewList(drinkId : Long): Result<List<Review>> {
        return reviewDataSource.getReviewList(drinkId).mapCatching { it.map{ it.toDrinkReview() } }
    }

    override suspend fun postReview(drinkId: Long, review : ReviewRequest): Result<Unit> {
        return reviewDataSource.postReview(drinkId, WriteDrinkReviewRequest(
            userId = review.userId,
            comment = review.comment,
            score = review.score,
            date = review.date
        ))
    }
}