package com.jaino.network.datasource.user

import com.jaino.network.model.user.AnalysisResponse
import com.jaino.network.model.user.AnalysisSentimentResponse
import com.jaino.network.remote.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val service : UserService
) : UserDataSource {

    override suspend fun getUserAnalyzeList(): List<AnalysisResponse> {
        return service.getAnalyzeList()
    }

    override suspend fun getUserAnalyzeInfo(id: Long): AnalysisSentimentResponse {
        return service.getSentimentAnalysis(id)
    }
}