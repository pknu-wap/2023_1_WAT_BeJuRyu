package com.jaino.model.analysis

data class SentimentAnalysis(
    val id: Long,
    val textExpression: String,
    val facialExpression: String,
    val sentiment: String,
    val level : Float,
    val date : String,
    val drinkId : Long,
    val name : String,
    val dosu : Double,
    val price : Int,
    val volume : Int,
    val drinkImage: String,
    val type : String,
    val sweetness : Int
){
    constructor() : this(
        0,
        "",
        "",
        "",
        0f,
        "",
        0,
        "",
        0.0,
        0,
        0,
        "",
        "",
        0
    )
}