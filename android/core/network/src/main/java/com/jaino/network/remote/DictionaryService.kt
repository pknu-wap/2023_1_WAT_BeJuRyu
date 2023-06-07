package com.jaino.network.remote

import com.jaino.network.model.response.base.BaseResponse
import com.jaino.network.model.response.dictionary.DrinkInfoResponse
import com.jaino.network.model.response.dictionary.DrinkListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryService {

    // 전체 주류 리스트 조회
    @GET("/drinks/all")
    suspend fun getDrinkList(): BaseResponse<List<DrinkInfoResponse>>

    // 이름으로 주류 조회
    @GET("/drinks/name/{name}")
    suspend fun getDrinkListByName(
        @Path("name") name: String
    ): DrinkListResponse

    // type 으로 주류 조회
    @GET("/drinks/type/{type}")
    suspend fun getDrinkListByType(
        @Path("type") type: String
    ): DrinkListResponse

    // id 값으로 주류 조회
    @GET("/drinks/{drink-id}")
    suspend fun getDrinkById(
        @Path("drink-id") id: Long
    ): DrinkInfoResponse
}
