package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkDataResponse

interface GetDrinkDataSource {

    suspend fun getDrinkById(id : Long): Result<DrinkDataResponse>

}