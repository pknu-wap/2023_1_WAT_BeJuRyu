package com.jaino.data.repository.rank

import com.jaino.model.rank.Rank

interface RankRepository{
    suspend fun getHighestRatedList(): Result<List<Rank>>

    suspend fun getMostReviewedList(): Result<List<Rank>>
}