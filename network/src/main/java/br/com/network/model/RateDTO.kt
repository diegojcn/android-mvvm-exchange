package br.com.network.model

data class RateDTO (
    val base: String?,
    val date: String?,
    val rates: Map<String, Float>?)