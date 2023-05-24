package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkInfoResponse
import com.jaino.network.remote.DictionaryService
import javax.inject.Inject

class GetDrinkListDataSourceImpl @Inject constructor(
    private val service : DictionaryService
): GetDrinkListDataSource{
    override suspend fun getDrinkList(): Result<List<DrinkInfoResponse>> =
        runCatching {
            // service.getDrinkList().data
            listOf(
                DrinkInfoResponse(
                name = "테라",
                image = "https://cdn.veluga.kr/files/supplier/13/drinks/24636_Main_-_RAMON.png",
                dosu = 4.5,
                volume = 500,
                price = 4500,
                type = "맥주"
            )
            )
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkInfoResponse>>(error)
        }

    override suspend fun getDrinkListByType(type: String): Result<List<DrinkInfoResponse>> =
        runCatching {
            service.getDrinkListByType(type).data
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkInfoResponse>>(error)
        }

}