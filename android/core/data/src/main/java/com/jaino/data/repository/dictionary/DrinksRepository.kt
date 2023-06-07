package com.jaino.data.repository.dictionary

import com.jaino.model.dictionary.Drink

interface DrinksRepository {
    suspend fun getDrinkList(): Result<List<Drink>>

    suspend fun getDrinkListByType(type: String): Result<List<Drink>>

    suspend fun getDrinkListByName(name: String): Result<List<Drink>>

    suspend fun getDrinkDataById(id: Long): Result<Drink>
}