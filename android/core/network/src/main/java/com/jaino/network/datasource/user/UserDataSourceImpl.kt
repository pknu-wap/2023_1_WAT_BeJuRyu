package com.jaino.network.datasource.user

import com.jaino.network.model.user.AnalysisResponse
import com.jaino.network.model.user.AnalysisSentimentResponse
import com.jaino.network.remote.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val service : UserService
) : UserDataSource {

    override suspend fun getUserAnalyzeList(): List<AnalysisResponse> {
        // return service.getAnalyzeList()
        return listOf(
            AnalysisResponse(
                id = 123,
                date = "2023.01.23(수) 오후 01시 32분"
            ),
            AnalysisResponse(
                id = 123,
                date = "2023.01.30(수) 오후 11시 22분"
            ),
            AnalysisResponse(
                id = 123,
                date = "2023.02.22(토) 오후 07시 33분"
            ),
            AnalysisResponse(
                id = 123,
                date = "2023.02.24(월) 오후 11시 15분"
            ),
            AnalysisResponse(
                id = 123,
                date = "2023.03.13(금) 오전 01시 45분"
            )
        )
    }

    override suspend fun getUserAnalyzeInfo(id: Long): AnalysisSentimentResponse {
        return service.getSentimentAnalysis(id)
    }
}