package com.jaino.model.analysis

data class SentimentAnalysis(
    val source : AnalysisSource,
    val result : AnalysisResult
){
    constructor() : this(
            AnalysisSource("", ""),
            AnalysisResult("", AnalysisDrink())
    )
}

data class AnalysisSource(
    val textExpression: String,
    val facialExpression: String
)

data class AnalysisResult(
    val sentiment: String,
    val drink: AnalysisDrink
)

data class AnalysisDrink(
    val id: Long,
    val name : String,
    val dosu : Double,
    val volume : Int,
    val price : Int,
    val type : String,
    val bottle_color : String
){
    constructor() : this(0, "", 0.0, 0,0, "", "")
}