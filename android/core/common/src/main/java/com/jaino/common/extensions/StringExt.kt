package com.jaino.common.extensions

fun String.toTypedEng(): String{
    return when(this){
        "맥주" -> "BEER"
        "소주" -> "SOJU"
        "막걸리" -> "MAKGEOLLI"
        "와인" -> "WINE"
        "리큐르" -> "LIQUEUR"
        "위스키" -> "WHISKEY"
        "사케" -> "RICE_WINE"
        "꼬냑" -> "BRANDY"
        "과실주" -> "FRUIT"
        "약주" -> "YAKJU"
        else -> ""
    }
}

fun String.toTypedKor(): String{
    return when(this){
        "BEER" -> "맥주"
        "SOJU" -> "소주"
        "MAKGEOLLI" -> "막걸리"
        "WINE" -> "와인"
        "LIQUEUR" -> "리큐르"
        "WHISKEY" -> "위스키"
        "RICE_WINE" -> "사케"
        "BRANDY" -> "꼬냑"
        "FRUIT" -> "과실주"
        "YAKJU" -> "약주"
        else -> ""
    }
}