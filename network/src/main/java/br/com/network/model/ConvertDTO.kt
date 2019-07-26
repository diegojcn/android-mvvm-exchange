package br.com.network.model

import java.util.*

data class ConvertDTO(
    val success: Boolean,
    val query: Map<String, Objects>,
    val info: Map<String, Float>,
    val historical: String,
    val date: Date,
    val result: Float

) {}