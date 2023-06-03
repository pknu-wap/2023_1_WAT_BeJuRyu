package com.jaino.data.repository.rank

import com.jaino.model.dictionary.Drink

interface RankRepository{
    suspend fun getHighestRatedList(): Result<List<Drink>>

    suspend fun getMostReviewedList(): Result<List<Drink>>
}