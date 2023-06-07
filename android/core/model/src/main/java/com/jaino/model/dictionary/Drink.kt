package com.jaino.model.dictionary

data class Drink(
    val id : Long,
    val name : String,
    val dosu : Double,
    val sweetness : Int,
    val price : Int,
    val volume : Int,
    val image : String,
    val type : String,
    val rating : Float,
    val reviewCount: Int
){
    constructor(): this(
        0,
        "",
        0.0,
        0,
        0,
        0,
        "",
        "",
        0.0f,
        0
    )
}