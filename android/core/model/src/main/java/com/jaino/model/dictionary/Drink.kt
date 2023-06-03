package com.jaino.model.dictionary

data class Drink(
    val id : Long,
    val name : String,
    val dosu : Double,
    val sweetness : Int,
    val price : Int,
    val volume : Int,
    val image : String,
    val type : String
){
    constructor(): this(
        0,
        "",
        0.0,
        0,
        0,
        0,
        "",
        ""
    )
}