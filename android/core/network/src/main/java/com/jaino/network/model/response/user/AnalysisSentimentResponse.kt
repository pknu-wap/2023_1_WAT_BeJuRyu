package com.jaino.network.model.response.user

import com.jaino.model.user.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("UserSentimentAnalysisResponse")
data class AnalysisSentimentResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data : AnalysisDataResponse
    )

@Serializable
data class AnalysisDataResponse(
    val source : AnalysisSourceResponse,
    val result : AnalysisResultResponse
){
    fun toAnalysisData() : AnalysisSentiment = AnalysisSentiment(
        source = source.toAnalysisSource(),
        result = result.toAnalysisResult()
    )
}

@Serializable
data class AnalysisSourceResponse(
    @SerialName("text_expression") val textExpression: String,
    @SerialName("facial_expression") val facialExpression: String
){
    fun toAnalysisSource() : AnalysisSource = AnalysisSource(
        textExpression = textExpression,
        facialExpression = facialExpression
    )
}

@Serializable
data class AnalysisResultResponse(
    val sentiment: String,
    val drink: AnalysisDrinkResponse
){
    fun toAnalysisResult(): AnalysisResult = AnalysisResult(
        sentiment = sentiment,
        drink = drink.toAnalysisDrink()
    )
}

@Serializable
data class AnalysisDrinkResponse(
    val id: Long,
    val name : String,
    val dosu : Double,
    val volume : Int,
    val price : Int,
    val type : String,
    @SerialName("bottle-color") val bottle_color : String
){
    fun toAnalysisDrink() : AnalysisDrink = AnalysisDrink(
        id = id,
        name = name,
        dosu = dosu,
        volume = volume,
        price = price,
        type = type,
        bottle_color = bottle_color
    )
}
