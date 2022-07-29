package com.example.ed_currencyconverter.Utils


sealed class ApiResponseStates<T>(val data: T? = null, val message: String? = null)  {

    class Success<T>(data: T) : ApiResponseStates<T>(data)
    class Error<T>(message: String, data: T? = null) : ApiResponseStates<T>(data, message)
    class Loading<T>(data: T? = null) :ApiResponseStates<T>(data)

}




