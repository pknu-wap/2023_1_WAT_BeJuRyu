package com.jaino.network.model.response.dictionary

import com.jaino.model.dictionary.DrinkInfo

data class DrinkInfoResponse(
    val name : String,
    val image : String,
    val dosu : Double,
    val volume : Int,
    val price : Int,
    val type : String
){
    fun toDrinkInfo(): DrinkInfo = DrinkInfo(
        name = name,
        image = image,
        dosu = dosu,
        volume = volume,
        price = price,
        type = type
    )
}