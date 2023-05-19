package com.jaino.network.model.response.review

import com.jaino.model.review.DrinkReview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkReviewResponse (
    @SerialName("review-id") val reviewId : Long,
    val nickname : String,
    val review : String,
    val score : Int
){
    fun toDrinkReview(): DrinkReview = DrinkReview(
        reviewId = reviewId,
        nickname = nickname,
        review = review,
        score = score
    )
}
