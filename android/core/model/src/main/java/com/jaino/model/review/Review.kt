package com.jaino.model.review

data class Review(
    val reviewId : Long,
    val comment : String,
    val score : Int,
    val date : String,
    val nickname : String
)