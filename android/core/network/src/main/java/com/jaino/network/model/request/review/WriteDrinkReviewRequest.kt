package com.jaino.network.model.request.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WriteDrinkReviewRequest (
    @SerialName("user_id") val userId : Long,
    val comment : String,
    val score : Int,
    val date : String
)
