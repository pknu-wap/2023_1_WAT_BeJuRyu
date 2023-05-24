package com.jaino.data.repository.dictionary

import com.jaino.model.dictionary.DrinkInfo
import com.jaino.network.datasource.dictionary.GetDrinkDataSource
import com.jaino.network.datasource.dictionary.GetDrinkListDataSource
import javax.inject.Inject

class DrinksRepositoryImpl @Inject constructor(
    private val drinkListDataSource: GetDrinkListDataSource,
    private val drinkDataSource: GetDrinkDataSource
) : DrinksRepository{
    override suspend fun getDrinkList(): Result<List<DrinkInfo>> {
        return drinkListDataSource.getDrinkList().mapCatching{ list ->
            list.map{ data -> data.toDrinkInfo()}
        }
    }

    override suspend fun getDrinkDataById(id: Long): Result<DrinkInfo> {
        return drinkDataSource.getDrinkById(id).mapCatching { data ->
            data.toDrinkInfo()
        }
    }

    override suspend fun getDrinkListByType(type: String): Result<List<DrinkInfo>> {
        return drinkListDataSource.getDrinkListByType(type).mapCatching { list ->
            list.map { data -> data.toDrinkInfo() }
        }
    }
}