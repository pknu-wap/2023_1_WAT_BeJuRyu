package com.jaino.model.dictionary

data class DrinkInfo(
    val name : String,
    val image : String,
    val dosu : Double,
    val volume : Int,
    val price : Int,
    val type : String
){
    constructor(): this(
        "",
        "",
        0.0,
        0,
        0,
        ""
    )
}