package com.example.ed_currencyconverter.Data.RemoteDataSource.Models

data class LatestRateResponse(
    val base: String,
    val date: String,
    val rates: HashMap<String,String>,
    val success: Boolean,
    val timestamp: Int
)