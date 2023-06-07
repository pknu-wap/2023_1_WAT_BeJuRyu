package model.review

data class ReviewModel (
    val reviewId : Long,
    val drinkName : String,
    val image : String,
    val nickname : String,
    val comment : String,
    val score : Int,
    val date : String
)
