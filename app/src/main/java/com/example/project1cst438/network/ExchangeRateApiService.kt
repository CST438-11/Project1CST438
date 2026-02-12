package com.example.project1cst438.network

import com.example.project1cst438.model.ExchangeRatesResponse
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://v6.exchangerate-api.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ExchangeRateApiService {
    @GET("v6/{apiKey}/latest/{base}")
    suspend fun getRates(
        @Path("apiKey") apiKey: String = "799d0fd82303937fa995d1c5",
        @Path("base") base: String = "USD"
    ): ExchangeRatesResponse
}


object ExchangeRateApi {
    val retrofitService : ExchangeRateApiService by lazy {
        retrofit.create(ExchangeRateApiService::class.java)
    }
}

