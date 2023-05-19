package com.jaino.network.model.response.user

import com.jaino.model.user.Analysis
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisResponse (
    val id: Long,
    val date: String
){
    fun toAnalyze() : Analysis = Analysis(
        id = id,
        date = date
    )
}