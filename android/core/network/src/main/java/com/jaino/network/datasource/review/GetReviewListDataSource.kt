package com.jaino.network.datasource.review

import com.jaino.network.model.response.review.DrinkReviewResponse

interface GetReviewListDataSource {
    suspend fun getReviewList(drinkId: Long) : Result<List<DrinkReviewResponse>>
}