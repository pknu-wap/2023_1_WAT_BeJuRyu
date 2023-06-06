package com.jaino.model.rank

data class Rank(
    val id : Long,
    val name : String,
    val type : String,
    val rating : Double,
    val reviewCount : Int,
    val image : String,
)