package com.jaino.network.model.response.rank

import com.jaino.model.rank.Rank
import kotlinx.serialization.Serializable

@Serializable
data class RankListResponse(
    val ranking : List<RankResponse>
)

@Serializable
data class RankResponse(
    val id : Long,
    val name : String,
    val type : String,
    val rating : Double,
    val reviewCount : Int,
    val image : String,
){
    fun toRank() : Rank = Rank(
        id = id,
        name = name,
        type = type,
        rating = rating,
        reviewCount = reviewCount,
        image = image
    )
}