package com.jaino.data.repository.review

import com.jaino.model.review.DrinkReview
import com.jaino.model.review.WriteReview
import com.jaino.network.datasource.review.GetReviewListDataSource
import com.jaino.network.datasource.review.PostReviewDataSource
import com.jaino.network.model.request.review.WriteDrinkReviewRequest
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val postReviewDataSource: PostReviewDataSource,
    private val getReviewListDataSource: GetReviewListDataSource
) : ReviewRepository{
    override suspend fun getReviewList(drinkId : Long): Result<List<DrinkReview>> {
        return getReviewListDataSource.getReviewList(drinkId).mapCatching { it.map{ it.toDrinkReview() } }
    }

    override suspend fun postReview(drinkId: Long, review: WriteReview): Result<Unit> {
        return postReviewDataSource.postReview(drinkId, WriteDrinkReviewRequest(
            userId = review.userId,
            comment = review.comment,
            score = review.score,
            date = review.date
        ))
    }
}