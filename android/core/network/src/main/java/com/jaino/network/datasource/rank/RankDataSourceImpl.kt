package com.jaino.network.datasource.rank

import com.jaino.network.model.response.dictionary.DrinkListResponse
import com.jaino.network.remote.RankService
import javax.inject.Inject

class RankDataSourceImpl @Inject constructor(
    private val service: RankService
): RankDataSource{
    override suspend fun getHighestRatedList(): Result<DrinkListResponse> =
        runCatching {
            service.getHighestRatedList()
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<DrinkListResponse>(error)
        }

    override suspend fun getMostReviewedList(): Result<DrinkListResponse> =
        runCatching {
            service.getMostReviewedList()
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<DrinkListResponse>(error)
        }
}