package com.jaino.network.model.request.analysis

import kotlinx.serialization.Serializable

@Serializable
data class AnalysisSourceRequest(
   val userId : Long,
   val textExpression : String,
   val facialExpression: String
)