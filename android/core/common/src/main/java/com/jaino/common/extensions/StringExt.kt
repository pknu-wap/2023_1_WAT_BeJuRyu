package com.jaino.common.extensions

fun String.toTypedEng(): String{
    return when(this){
        "맥주" -> "BEER"
        "소주" -> "SOJU"
        "막걸리" -> "MAKGEOLLI"
        "와인" -> "WINE"
        "리큐어" -> "LIQUEUR"
        "위스키" -> "WHISKEY"
        "사케" -> "RICE_WINE"
        "꼬냑" -> "BRANDY"
        "과실주" -> "FRUIT"
        "약주" -> "YAKJU"
        else -> ""
    }
}