package com.jaino.data.repository.analysis

import com.jaino.model.analysis.AnalysisId
import com.jaino.model.analysis.SentimentAnalysis
import com.jaino.model.analysis.AnalysisHistory
import com.jaino.network.datasource.analysis.AnalysisDataSource
import javax.inject.Inject

class AnalysisRepositoryImpl @Inject constructor(
    private val source: AnalysisDataSource,
) : AnalysisRepository {

    override suspend fun postAnalysisSource(
        userId: Long,
        textExpression: String,
        facialExpression: String
    ): Result<AnalysisId> =
        source.postAnalysisSource(userId, textExpression, facialExpression).mapCatching {
            it.toAnalysisId()
        }

    override suspend fun getAnalysisList(): Result<List<AnalysisHistory>> =
        source.getAnalysisList().mapCatching { list ->
            list.map{ it.toAnalyzeHistory() }
        }

    override suspend fun getSentimentAnalysis(analysisId: Long): Result<SentimentAnalysis> =
        source.getSentimentAnalysis(analysisId).mapCatching { it.toSentimentAnalysis() }
}