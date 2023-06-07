package com.jaino.network.model.response.analysis

import com.jaino.model.analysis.AnalysisId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisIdResponse(
    @SerialName("id") val analysisId : Long
){
    fun toAnalysisId(): AnalysisId = AnalysisId(analysisId)
}