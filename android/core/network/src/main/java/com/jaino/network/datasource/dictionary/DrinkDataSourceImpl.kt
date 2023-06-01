package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkInfoResponse
import com.jaino.network.model.response.dictionary.DrinkListResponse
import com.jaino.network.remote.DictionaryService
import javax.inject.Inject

class DrinkDataSourceImpl @Inject constructor(
    private val service : DictionaryService
): DrinkDataSource{
    override suspend fun getDrinkList(): Result<List<DrinkInfoResponse>> =
        runCatching {
            service.getDrinkList().data
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkInfoResponse>>(error)
        }

    override suspend fun getDrinkListByType(type: String): Result<DrinkListResponse> =
        runCatching {
            service.getDrinkListByType(type)
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkInfoResponse>>(error)
        }

    override suspend fun getDrinkByName(name: String): Result<DrinkListResponse> =
        runCatching {
            service.getDrinkListByName(name)
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkInfoResponse>>(error)
        }

    override suspend fun getDrinkById(id: Long): Result<DrinkInfoResponse> =
        runCatching {
            service.getDrinkById(id)
        }.onFailure {error ->
            error.printStackTrace()
            Result.failure<DrinkInfoResponse>(error)
        }
}