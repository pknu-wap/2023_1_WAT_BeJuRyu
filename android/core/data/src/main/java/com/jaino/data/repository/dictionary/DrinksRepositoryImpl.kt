package com.jaino.data.repository.dictionary

import com.jaino.model.dictionary.DrinkData
import com.jaino.network.datasource.dictionary.GetDrinkDataSource
import com.jaino.network.datasource.dictionary.GetDrinkListDataSource
import javax.inject.Inject

class DrinksRepositoryImpl @Inject constructor(
    private val drinkListDataSource: GetDrinkListDataSource,
    private val drinkDataSource: GetDrinkDataSource
) : DrinksRepository{
    override suspend fun getDrinkList(): Result<List<DrinkData>> {
        return drinkListDataSource.getDrinkList().mapCatching{ list ->
            list.map{ data -> data.toDrinkData()}
        }
    }

    override suspend fun getDrinkDataById(id: Long): Result<DrinkData> {
        return drinkDataSource.getDrinkById(id).mapCatching { data ->
            data.toDrinkData()
        }
    }
}