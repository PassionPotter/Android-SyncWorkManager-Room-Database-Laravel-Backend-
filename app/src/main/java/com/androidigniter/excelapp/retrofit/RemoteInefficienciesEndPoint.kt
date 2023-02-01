package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteInefficienciesEndPoint {

    @Headers("Content-Type: application/json")
    @POST("inefficiencies")
    fun saveInefficiencies(@HeaderMap headers:Map<String, String>, @Body task: InefficienciesResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchInefficiencies")
    fun getInefficiencies(): Call<List<InefficienciesResponse>>

    @Headers("Content-Type: application/json")
    @POST("crane-inefficiencies")
    fun saveCraneInefficiencies(@HeaderMap headers:Map<String, String>, @Body task: CraneInefficienciesResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("crane-fetchInefficiencies")
    fun getCraneInefficiencies(): Call<List<CraneInefficienciesResponse>>


}