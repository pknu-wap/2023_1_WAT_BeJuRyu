package com.jaino.network.datasource.analysis

import com.jaino.network.model.response.analysis.AnalysisIdResponse
import com.jaino.network.model.response.analysis.AnalysisHistoryResponse
import com.jaino.network.model.response.user.SentimentAnalysisResponse

interface AnalysisDataSource {
    suspend fun postAnalysisSource(
        userId: Long, textExpression: String, facialExpression: String
    ): Result<AnalysisIdResponse>

    suspend fun getAnalysisList() : Result<List<AnalysisHistoryResponse>>

    suspend fun getSentimentAnalysis(analysisId: Long) : Result<SentimentAnalysisResponse>
}