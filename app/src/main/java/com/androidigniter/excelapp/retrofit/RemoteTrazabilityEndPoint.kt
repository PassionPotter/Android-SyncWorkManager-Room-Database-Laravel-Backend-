package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteTrazabilityEndPoint {

    @Headers("Content-Type: application/json")
    @POST("trazability")
    fun saveTrazability(@HeaderMap headers:Map<String, String>, @Body task: TrazabilityResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchTrazability")
    fun getTrazability(): Call<List<TrazabilityResponse>>

    @Headers("Content-Type: application/json")
    @POST("crane-trazability")
    fun saveCraneTrazability(@HeaderMap headers:Map<String, String>, @Body task: CraneTrazabilityResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("crane-fetchTrazability")
    fun getCraneTrazability(): Call<List<CraneTrazabilityResponse>>



}