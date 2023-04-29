package com.jaino.network.datasource.dictionary

import com.jaino.network.model.dictionary.DrinkDataResponse

interface GetDrinkListDataSource {
    suspend fun getDrinkList() : Result<List<DrinkDataResponse>>
}