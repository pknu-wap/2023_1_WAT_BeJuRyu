package com.jaino.data.repository.user

import com.jaino.model.user.AnalysisSentiment
import com.jaino.model.user.Analysis
import com.jaino.network.datasource.user.UserDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val source: UserDataSource
) : UserRepository{

    override suspend fun getUserAnalyzeList(): List<Analysis> {
        return source.getUserAnalyzeList().map{ it.toAnalyze() }
    }

    override suspend fun getUserAnalysisInfo(id: Long): AnalysisSentiment {
        return source.getUserAnalyzeInfo(id).data.toAnalysisData()
    }
}