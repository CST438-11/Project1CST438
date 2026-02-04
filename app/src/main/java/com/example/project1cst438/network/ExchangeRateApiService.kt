package com.example.project1cst438.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ExchangeRateApiService {
    @GET("USD")
    suspend fun getRates(): String
}

object ExchangeRateApi {
    val retrofitService : ExchangeRateApiService by lazy {
        retrofit.create(ExchangeRateApiService::class.java)
    }
}

//class ExchangeRateApiService {
//
//}