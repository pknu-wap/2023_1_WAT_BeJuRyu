package com.jaino.network.model.response.review

import com.jaino.model.review.Review
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DrinkReviewListResponse(
    val reviews : List<DrinkReviewResponse>
)
@Serializable
data class DrinkReviewResponse (
    @SerialName("id") val reviewId : Long,
    val score : Int,
    val date : String,
    val comment : String,
    val nickname : String,
    @SerialName("drinkId") val drinkId : Long
    ){
    fun toDrinkReview(): Review = Review(
        reviewId = reviewId,
        comment = comment,
        score = score,
        date = date,
        nickname = nickname,
    )
}
