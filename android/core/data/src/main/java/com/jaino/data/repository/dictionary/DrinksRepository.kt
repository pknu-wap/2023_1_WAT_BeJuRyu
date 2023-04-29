package com.jaino.data.repository.dictionary

import com.jaino.model.dictionary.DrinkData

interface DrinksRepository {
    suspend fun getDrinkList(): Result<List<DrinkData>>
}