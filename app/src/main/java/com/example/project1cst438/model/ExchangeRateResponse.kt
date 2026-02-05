package com.example.project1cst438.model

data class ExchangeRateResponse(
    val base_code: String,
    val conversion_rates: Map<String, Double>
)