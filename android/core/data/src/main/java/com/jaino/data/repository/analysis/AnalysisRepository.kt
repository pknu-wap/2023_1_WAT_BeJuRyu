package com.jaino.data.repository.analysis

import com.jaino.model.analysis.AnalysisId
import com.jaino.model.analysis.SentimentAnalysis
import com.jaino.model.analysis.AnalysisHistory

interface AnalysisRepository {

    suspend fun postAnalysisSource(
        userId: Long, textExpression: String, facialExpression: String
    ): Result<AnalysisId>
    suspend fun getAnalysisList() : Result<List<AnalysisHistory>>

    suspend fun getSentimentAnalysis(analysisId: Long) : Result<SentimentAnalysis>
}