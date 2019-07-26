package br.com.network.api

import br.com.network.model.SupportedSymbolsDTO
import io.reactivex.Single
import retrofit2.http.GET

import retrofit2.http.Query

interface ISymbolApi {

    @GET("/api/symbols")
    fun getSymbol(@Query("access_key") access_key: String): Single<SupportedSymbolsDTO>

}