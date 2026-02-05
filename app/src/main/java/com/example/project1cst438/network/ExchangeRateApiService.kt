package com.example.project1cst438.network

import com.example.project1cst438.model.ExchangeRateResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://v6.exchangerate-api.com/v6/799d0fd82303937fa995d1c5/latest/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ExchangeRateApiService {
    @GET("USD")
    suspend fun getRates(): ExchangeRateResponse
}

object ExchangeRateApi {
    val retrofitService : ExchangeRateApiService by lazy {
        retrofit.create(ExchangeRateApiService::class.java)
    }
}

