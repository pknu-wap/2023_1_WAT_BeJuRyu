package com.jaino.model.review

data class ReviewItem (
    val drinkName : String,
    val image : String,
    val reviewId : Long,
    val nickname : String,
    val review : String,
    val score : Int
)
