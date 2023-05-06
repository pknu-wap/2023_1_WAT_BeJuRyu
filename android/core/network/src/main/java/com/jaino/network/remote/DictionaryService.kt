package com.jaino.network.remote

import com.jaino.network.model.base.BaseResponse
import com.jaino.network.model.dictionary.DrinkDataResponse
import com.jaino.network.model.dictionary.DrinkListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface DictionaryService {

    // 전체 주류 리스트 조회
    @GET("/drinks/all")
    fun getDrinkList(): DrinkListResponse

    // type 으로 주류 조회
    @GET("/drinks/{type}")
    fun getDrinkListByType(
        @Query("type") type: String
    ): DrinkListResponse

    // id 값으로 주류 조회
    @GET("/drinks/{drink-id}")
    fun getDrinkById(
        @Query("drink-id") id: Long
    ): BaseResponse<DrinkDataResponse>
}
