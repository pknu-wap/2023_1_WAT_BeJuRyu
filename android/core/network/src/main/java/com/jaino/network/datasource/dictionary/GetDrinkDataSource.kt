package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkInfoResponse

interface GetDrinkDataSource {

    suspend fun getDrinkById(id : Long): Result<DrinkInfoResponse>

}