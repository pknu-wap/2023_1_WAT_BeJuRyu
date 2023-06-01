package com.jaino.network.model.request.review

import kotlinx.serialization.Serializable

@Serializable
data class WriteDrinkReviewRequest (
    val userId : Long,
    val comment : String,
    val score : Int,
    val date : String
)
