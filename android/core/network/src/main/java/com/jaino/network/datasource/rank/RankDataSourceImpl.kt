package com.jaino.network.datasource.rank

import com.jaino.network.model.response.rank.RankListResponse
import com.jaino.network.remote.RankService
import javax.inject.Inject

class RankDataSourceImpl @Inject constructor(
    private val service: RankService
): RankDataSource{
    override suspend fun getHighestRatedList(): Result<RankListResponse> =
        runCatching {
            service.getHighestRatedList()
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<RankListResponse>(error)
        }

    override suspend fun getMostReviewedList(): Result<RankListResponse> =
        runCatching {
            service.getMostReviewedList()
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<RankListResponse>(error)
        }
}