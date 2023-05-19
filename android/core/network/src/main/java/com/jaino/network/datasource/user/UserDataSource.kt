package com.jaino.network.datasource.user

import com.jaino.network.model.response.user.AnalysisSentimentResponse
import com.jaino.network.model.response.user.AnalysisResponse

interface UserDataSource {
    suspend fun getUserAnalyzeList() : List<AnalysisResponse>

    suspend fun getUserAnalyzeInfo(id: Long) : AnalysisSentimentResponse
}