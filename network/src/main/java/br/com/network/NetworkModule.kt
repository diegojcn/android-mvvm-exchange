package br.com.network


import br.com.network.api.IExchangeRatesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.HttpUrl


val networkModule = module {

    single<OkHttpClient>() { provideDefaultOkHttpClient() }
    single<Retrofit>() { provideRetrofit(get()) }
    single<IExchangeRatesApi> { provideExchangeRates(get()) }

}

fun provideDefaultOkHttpClient(): OkHttpClient {

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
}


fun provideRetrofit(client: OkHttpClient): Retrofit {

    val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(SERVER_BASE_URL)
            .build()

    return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

fun provideExchangeRates(retrofit: Retrofit): IExchangeRatesApi = retrofit.create(IExchangeRatesApi::class.java)



