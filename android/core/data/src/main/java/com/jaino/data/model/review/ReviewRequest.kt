package com.jaino.data.model.review

data class ReviewRequest(
    val userId : Long,
    val comment : String,
    val score : Int,
    val date : String
)