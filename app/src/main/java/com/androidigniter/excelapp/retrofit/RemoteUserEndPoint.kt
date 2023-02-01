package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.CompanyResponse
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface RemoteUserEndPoint {

    @Headers("Content-Type: application/json")
    @GET("checkUser")
    fun checkUser(@HeaderMap headers:Map<String, String>): Call<ResponseBody>

}