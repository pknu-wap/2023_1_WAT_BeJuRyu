package com.jaino.network.remote

import com.jaino.network.model.request.analysis.AnalysisSourceRequest
import com.jaino.network.model.response.analysis.AnalysisIdResponse
import com.jaino.network.model.response.base.BaseResponse
import com.jaino.network.model.response.analysis.AnalysisHistoryResponse
import com.jaino.network.model.response.analysis.SentimentAnalysisResponse
import retrofit2.http.*

interface AnalysisService {

    @POST("/analyze/sources")
    suspend fun postAnalysisSource(
        @Body analysisSourceRequest: AnalysisSourceRequest
    ) : BaseResponse<AnalysisIdResponse>

    @GET("/analyze")
    suspend fun getAnalyzeList() : List<AnalysisHistoryResponse>

    @GET("/analyze/{analysis-id}")
    suspend fun getSentimentAnalysis(
        @Path("analysisId") analysisId : Long
    ) : BaseResponse<SentimentAnalysisResponse>
}