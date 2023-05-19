package com.jaino.network.remote

import com.jaino.network.model.response.user.AnalysisResponse
import com.jaino.network.model.response.user.AnalysisSentimentResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/users/my-analyze")
    suspend fun getAnalyzeList() : List<AnalysisResponse>

    @GET("/users/my-analyze/{id}")
    suspend fun getSentimentAnalysis(
        @Path("id") id : Long
    ) : AnalysisSentimentResponse

}