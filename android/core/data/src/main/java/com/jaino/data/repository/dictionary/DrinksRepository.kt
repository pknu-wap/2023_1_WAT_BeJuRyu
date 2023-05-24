package com.jaino.data.repository.dictionary

import com.jaino.model.dictionary.DrinkInfo

interface DrinksRepository {
    suspend fun getDrinkList(): Result<List<DrinkInfo>>

    suspend fun getDrinkDataById(id: Long): Result<DrinkInfo>

    suspend fun getDrinkListByType(type: String): Result<List<DrinkInfo>>
}