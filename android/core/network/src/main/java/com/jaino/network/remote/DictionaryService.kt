package com.jaino.network.remote

import com.jaino.network.model.dictionary.DrinkListResponse
import retrofit2.http.GET


interface DictionaryService {

    @GET("/drinks/all")
    fun getDrinkList(): DrinkListResponse
}