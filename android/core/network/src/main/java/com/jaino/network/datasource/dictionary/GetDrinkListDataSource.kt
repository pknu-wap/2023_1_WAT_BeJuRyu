package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkDataResponse

interface GetDrinkListDataSource {
    suspend fun getDrinkList() : Result<List<DrinkDataResponse>>

    suspend fun getDrinkListByType(type: String) : Result<List<DrinkDataResponse>>
}