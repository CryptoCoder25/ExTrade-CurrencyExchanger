package com.example.ed_currencyconverter.Data.RemoteDataSource

import com.example.ed_currencyconverter.Data.RemoteDataSource.Models.LatestRateResponse
import com.example.ed_currencyconverter.Utils.Constant
import retrofit2.Response
import retrofit2.http.*

interface WebService {


    @GET("latest")
 suspend  fun getLatestRates(@Header("apikey") appKey: String, @Query("base") base: String): Response<LatestRateResponse>

}