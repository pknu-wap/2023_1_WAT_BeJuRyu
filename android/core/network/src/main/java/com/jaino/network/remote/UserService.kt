package com.jaino.network.remote

import com.jaino.network.model.user.AnalysisResponse
import com.jaino.network.model.user.AnalysisSentimentResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/users/my-analyze")
    fun getAnalyzeList() : List<AnalysisResponse>

    @GET("/users/my-analyze/{id}")
    fun getSentimentAnalysis(
        @Path("id") id : Long
    ) : AnalysisSentimentResponse

}