package com.jaino.network.datasource.dictionary

import com.jaino.network.model.response.dictionary.DrinkDataResponse
import com.jaino.network.remote.DictionaryService
import javax.inject.Inject

class GetDrinkDataSourceImpl @Inject constructor(
    private val service: DictionaryService
): GetDrinkDataSource{
    override suspend fun getDrinkById(id: Long): Result<DrinkDataResponse> =
        runCatching {
            // service.getDrinkById(id).data
            DrinkDataResponse(
                name = "테라",
                image = "https://cdn.veluga.kr/files/supplier/13/drinks/24636_Main_-_RAMON.png",
                dosu = 4.5,
                volume = 500,
                price = 4500,
                type = "맥주"
            )
        }.onFailure {error ->
            error.printStackTrace()
            Result.failure<DrinkDataResponse>(error)
        }
}
