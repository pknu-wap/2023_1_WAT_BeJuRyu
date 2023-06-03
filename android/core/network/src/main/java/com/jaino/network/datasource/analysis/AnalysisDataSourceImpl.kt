package com.jaino.network.datasource.analysis

import com.jaino.network.model.request.analysis.AnalysisSourceRequest
import com.jaino.network.model.response.analysis.AnalysisIdResponse
import com.jaino.network.model.response.analysis.AnalysisHistoryResponse
import com.jaino.network.model.response.analysis.SentimentAnalysisResponse
import com.jaino.network.remote.AnalysisService
import javax.inject.Inject

class AnalysisDataSourceImpl @Inject constructor(
    private val service : AnalysisService
): AnalysisDataSource {
    override suspend fun postAnalysisSource(
        analysisSourceRequest: AnalysisSourceRequest
    ): Result<AnalysisIdResponse> =
        runCatching {
            service.postAnalysisSource(analysisSourceRequest).data
        }.onFailure { error ->
            Result.failure<AnalysisIdResponse>(error)
        }

    override suspend fun getAnalysisList(): Result<List<AnalysisHistoryResponse>> =
        runCatching {
            // service.getAnalyzeList()
            listOf(
                AnalysisHistoryResponse(
                    23,
                    "2023.06.23",
                    "기쁨"
                )
            )
        }.onFailure { error ->
            Result.failure<List<AnalysisHistoryResponse>>(error)
        }

    override suspend fun getSentimentAnalysis(analysisId: Long): Result<SentimentAnalysisResponse> =
        runCatching {
            service.getSentimentAnalysis(analysisId).data
        }.onFailure { error ->
            Result.failure<SentimentAnalysisResponse>(error)
        }
}