package com.jaino.data.repository.rank

import com.jaino.model.rank.Rank
import com.jaino.network.datasource.rank.RankDataSource
import javax.inject.Inject

class RankRepositoryImpl @Inject constructor(
    private val dataSource: RankDataSource
): RankRepository{
    override suspend fun getHighestRatedList(): Result<List<Rank>> {
        return dataSource.getHighestRatedList().mapCatching { list ->
            list.ranking.map{ it.toRank() }
        }
    }

    override suspend fun getMostReviewedList(): Result<List<Rank>> {
        return dataSource.getMostReviewedList().mapCatching { list ->
            list.ranking.map { it.toRank() }
        }
    }
}