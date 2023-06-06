package com.jaino.network.datasource.rank

import com.jaino.network.model.response.rank.RankListResponse

interface RankDataSource {

    suspend fun getHighestRatedList(): Result<RankListResponse>

    suspend fun getMostReviewedList(): Result<RankListResponse>
}