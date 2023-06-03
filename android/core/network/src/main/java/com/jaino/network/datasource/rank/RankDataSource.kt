package com.jaino.network.datasource.rank

import com.jaino.network.model.response.dictionary.DrinkListResponse

interface RankDataSource {

    suspend fun getHighestRatedList(): Result<DrinkListResponse>

    suspend fun getMostReviewedList(): Result<DrinkListResponse>
}