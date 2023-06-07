package com.jaino.network.model.response.analysis

import com.jaino.model.analysis.AnalysisHistory
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisHistoryResponse (
    val id: Long,
    val date: String,
    val sentiment : String
){
    fun toAnalyzeHistory() : AnalysisHistory = AnalysisHistory(
        id = id,
        date = date.replace("T", " ").slice(0 until date.length-3),
        sentiment = sentiment
    )
}