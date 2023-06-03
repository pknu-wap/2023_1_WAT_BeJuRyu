package com.jaino.data.repository.rank

import com.jaino.model.dictionary.Drink
import com.jaino.network.datasource.rank.RankDataSource
import javax.inject.Inject

class RankRepositoryImpl @Inject constructor(
    private val dataSource: RankDataSource
): RankRepository{
    override suspend fun getHighestRatedList(): Result<List<Drink>> {
        return dataSource.getHighestRatedList().mapCatching { list ->
            list.drinks.map{ it.toDrinkInfo() }
        }
    }

    override suspend fun getMostReviewedList(): Result<List<Drink>> {
        return dataSource.getMostReviewedList().mapCatching { list ->
            list.drinks.map { it.toDrinkInfo() }
        }
    }
}