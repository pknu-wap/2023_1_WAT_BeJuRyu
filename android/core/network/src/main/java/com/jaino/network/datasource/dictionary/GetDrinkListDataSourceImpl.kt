package com.jaino.network.datasource.dictionary

import com.jaino.network.model.dictionary.DrinkDataResponse
import com.jaino.network.remote.DictionaryService
import javax.inject.Inject

class GetDrinkListDataSourceImpl @Inject constructor(
    private val service : DictionaryService
): GetDrinkListDataSource{
    override suspend fun getDrinkList(): Result<List<DrinkDataResponse>> =
        runCatching {
            service.getDrinkList().data
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkDataResponse>>(error)
        }

    override suspend fun getDrinkListByType(type: String): Result<List<DrinkDataResponse>> =
        runCatching {
            service.getDrinkListByType(type).data
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkDataResponse>>(error)
        }

}