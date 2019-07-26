package br.com.network.model

data class SupportedSymbolsDTO(
    val success: Boolean?,
    val symbols: Map<String, String>?
) {
}