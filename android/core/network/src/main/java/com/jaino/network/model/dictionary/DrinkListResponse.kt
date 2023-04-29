package com.jaino.network.model.dictionary

import com.jaino.model.dictionary.DrinkData

data class DrinkListResponse(
    val status : Int,
    val success: Boolean,
    val message: String,
    val data: List<DrinkDataResponse>
)

data class DrinkDataResponse(
    val name : String,
    val dosu : Double,
    val volume : Int,
    val price : Int,
    val type : String
){
    fun toDrinkData(): DrinkData = DrinkData(
        name = name,
        dosu = dosu,
        volume = volume,
        price = price,
        type = type
    )
}