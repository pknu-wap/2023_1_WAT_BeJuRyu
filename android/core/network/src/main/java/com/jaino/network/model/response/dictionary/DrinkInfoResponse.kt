package com.jaino.network.model.response.dictionary

import com.jaino.model.dictionary.Drink
import kotlinx.serialization.Serializable

@Serializable
data class DrinkListResponse(
    val drinks : List<DrinkInfoResponse>
)

@Serializable
data class DrinkInfoResponse(
    val id : Long,
    val name : String,
    val dosu : Double,
    val sweetness : Int,
    val price : Int,
    val volume : Int,
    val image : String,
    val type : String
){
    fun toDrinkInfo(): Drink = Drink(
        id = id,
        name = name,
        dosu = dosu,
        sweetness = sweetness,
        price = price,
        volume = volume,
        image = image,
        type = type
    )
}