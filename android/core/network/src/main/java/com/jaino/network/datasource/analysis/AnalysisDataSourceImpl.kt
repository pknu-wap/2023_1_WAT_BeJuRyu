package com.jaino.network.datasource.analysis

import com.jaino.network.model.response.analysis.AnalysisIdResponse
import com.jaino.network.model.response.analysis.AnalysisHistoryResponse
import com.jaino.network.model.response.user.SentimentAnalysisResponse
import com.jaino.network.remote.AnalysisService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class AnalysisDataSourceImpl @Inject constructor(
    private val service : AnalysisService
): AnalysisDataSource {
    override suspend fun postAnalysisSource(
        userId: Long,
        textExpression: String,
        facialExpression: String
    ): Result<AnalysisIdResponse> =
        runCatching {
            val facialFile = facialExpression.toRequestBody("image/*".toMediaTypeOrNull())
            service.postAnalysisSource(
                userId = userId,
                textExpression = textExpression,
                facialExpression = MultipartBody.Part.createFormData(
                    "file",
                    "image.jpg",
                    facialFile
                )
            ).data
        }.onFailure { error ->
            Result.failure<AnalysisIdResponse>(error)
        }

    override suspend fun getAnalysisList(): Result<List<AnalysisHistoryResponse>> =
        runCatching {
            service.getAnalyzeList()
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