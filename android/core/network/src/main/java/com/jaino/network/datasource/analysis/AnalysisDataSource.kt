package com.jaino.network.datasource.analysis

import com.jaino.network.model.request.analysis.AnalysisSourceRequest
import com.jaino.network.model.response.analysis.AnalysisIdResponse
import com.jaino.network.model.response.analysis.AnalysisHistoryResponse
import com.jaino.network.model.response.user.SentimentAnalysisResponse

interface AnalysisDataSource {
    suspend fun postAnalysisSource(
        analysisSourceRequest: AnalysisSourceRequest
    ): Result<AnalysisIdResponse>

    suspend fun getAnalysisList() : Result<List<AnalysisHistoryResponse>>

    suspend fun getSentimentAnalysis(analysisId: Long) : Result<SentimentAnalysisResponse>
}