package com.jaino.model.review

data class DrinkReview(
    val reviewId : Long,
    val nickname : String,
    val review : String,
    val score : Int
)