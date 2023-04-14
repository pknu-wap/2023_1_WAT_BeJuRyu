package com.jaino.data.repository.user

import com.jaino.model.user.AnalysisSentiment
import com.jaino.model.user.Analysis

interface UserRepository {
    suspend fun getUserAnalyzeList() : List<Analysis>

    suspend fun getUserAnalysisInfo(id: Long) : AnalysisSentiment
}