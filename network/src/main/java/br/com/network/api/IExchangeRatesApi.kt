package br.com.network.api

import br.com.network.model.RateDTO
import io.reactivex.Single
import retrofit2.http.GET

import retrofit2.http.Query

interface IExchangeRatesApi {

    @GET("/latest")
    fun getLatestRateBaseEuro(): Single<RateDTO>

    @GET("/latest")
    fun getLatestByBase(@Query("base") base: String): Single<RateDTO>

}