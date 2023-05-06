package com.jaino.network.datasource.dictionary

import com.jaino.network.model.dictionary.DrinkDataResponse
import com.jaino.network.remote.DictionaryService
import javax.inject.Inject

class GetDrinkDataSourceImpl @Inject constructor(
    private val service: DictionaryService
): GetDrinkDataSource{
    override suspend fun getDrinkById(id: Long): Result<DrinkDataResponse> =
        runCatching {
            service.getDrinkById(id).data
        }.onFailure {error ->
            error.printStackTrace()
            Result.failure<DrinkDataResponse>(error)
        }
}
