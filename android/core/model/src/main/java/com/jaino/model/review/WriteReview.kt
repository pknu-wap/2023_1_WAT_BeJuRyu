package com.jaino.model.review

data class WriteReview(
    val userId : Long,
    val comment : String,
    val score : Int,
    val date : String
)