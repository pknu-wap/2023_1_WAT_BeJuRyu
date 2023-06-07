package com.jaino.network.model.response.analysis

import com.jaino.model.analysis.SentimentAnalysis
import kotlinx.serialization.Serializable


@Serializable
data class SentimentAnalysisResponse(
    val id: Long,
    val textExpression: String,
    val facialExpression: String,
    val sentiment: String,
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
    fun toSentimentAnalysis() : SentimentAnalysis = SentimentAnalysis(
        id = id,
        textExpression = textExpression,
        facialExpression = facialExpression,
        sentiment = sentiment.filter { it.isLetter() },
        level = sentiment.filter { it.isDigit() }.toFloat(),
        date = date,
        drinkId = drinkId,
        name = name,
        dosu = dosu,
        price = price,
        volume = volume,
        drinkImage = drinkImage,
        type = type,
        sweetness = sweetness
    )
}