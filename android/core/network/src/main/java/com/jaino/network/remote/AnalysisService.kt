package com.jaino.network.remote

import com.jaino.network.model.response.analysis.AnalysisIdResponse
import com.jaino.network.model.response.base.BaseResponse
import com.jaino.network.model.response.analysis.AnalysisHistoryResponse
import com.jaino.network.model.response.user.SentimentAnalysisResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface AnalysisService {

    @Multipart
    @POST("/analyze/source")
    suspend fun postAnalysisSource(
        @Part("user-id") userId : Long,
        @Part("text-expression") textExpression : String,
        @Part("facial-expression") facialExpression: MultipartBody.Part,
    ) : BaseResponse<AnalysisIdResponse>

    @GET("/analyze")
    suspend fun getAnalyzeList() : List<AnalysisHistoryResponse>

    @GET("/analyze/{analysis-id}")
    suspend fun getSentimentAnalysis(
        @Path("analysisId") analysisId : Long
    ) : BaseResponse<SentimentAnalysisResponse>
}