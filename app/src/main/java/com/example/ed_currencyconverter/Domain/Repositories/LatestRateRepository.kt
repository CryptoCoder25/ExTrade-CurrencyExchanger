package com.example.ed_currencyconverter.Domain.Repositories

import android.util.Log
import com.example.ed_currencyconverter.Data.RemoteDataSource.Models.LatestRateResponse
import com.example.ed_currencyconverter.Data.RemoteDataSource.WebService
import com.example.ed_currencyconverter.Utils.ApiResponseStates
import com.example.ed_currencyconverter.Utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject



class LatestRateRepository @Inject constructor(private var api: WebService) {


    suspend fun getLatestRates(base: String): ApiResponseStates<LatestRateResponse> {
        return try {

            val response = api.getLatestRates(Constant.API_KEY,base)
            val result = response.body()

            if(response.isSuccessful && result != null) {

                ApiResponseStates.Success(result)

            } else {
                Log.d(Constant.LATEST_RATE_REPOSITORY,  response.toString())
                ApiResponseStates.Error(response.message())

            }
        } catch(e: Exception) {
            Log.d(Constant.LATEST_RATE_REPOSITORY, e.message.toString())
            ApiResponseStates.Error(e.message ?: "An error occured")
        } catch(e: HttpException) {
            Log.d(Constant.LATEST_RATE_REPOSITORY, e.message.toString())
            ApiResponseStates.Error(e.message ?: "An error occured")
        }
    }






}