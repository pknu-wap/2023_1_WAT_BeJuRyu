package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkInfoResponse

interface GetDrinkListDataSource {
    suspend fun getDrinkList() : Result<List<DrinkInfoResponse>>

    suspend fun getDrinkListByType(type: String) : Result<List<DrinkInfoResponse>>
}