package com.jaino.data.repository.dictionary

import com.jaino.model.dictionary.Drink
import com.jaino.network.datasource.dictionary.DrinkDataSource
import javax.inject.Inject

class DrinksRepositoryImpl @Inject constructor(
    private val drinkDataSource: DrinkDataSource,
) : DrinksRepository{
    override suspend fun getDrinkList(): Result<List<Drink>> {
        return drinkDataSource.getDrinkList().mapCatching{ list ->
            list.map{ data -> data.toDrinkInfo()}
        }
    }

    override suspend fun getDrinkListByType(type: String): Result<List<Drink>> {
        return drinkDataSource.getDrinkListByType(type).mapCatching { list ->
            list.drinks.map { data -> data.toDrinkInfo() }
        }
    }

    override suspend fun getDrinkListByName(name: String): Result<List<Drink>> {
        return drinkDataSource.getDrinkByName(name).mapCatching { list ->
            list.drinks.map { data -> data.toDrinkInfo() }
        }
    }

    override suspend fun getDrinkDataById(id: Long): Result<Drink> {
        return drinkDataSource.getDrinkById(id).mapCatching { data ->
            data.toDrinkInfo()
        }
    }
}