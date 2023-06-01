package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkInfoResponse
import com.jaino.network.model.response.dictionary.DrinkListResponse

interface DrinkDataSource {
    suspend fun getDrinkList() : Result<List<DrinkInfoResponse>>

    suspend fun getDrinkListByType(type: String) : Result<DrinkListResponse>

    suspend fun getDrinkByName(name: String) : Result<DrinkListResponse>

    suspend fun getDrinkById(id : Long): Result<DrinkInfoResponse>

}