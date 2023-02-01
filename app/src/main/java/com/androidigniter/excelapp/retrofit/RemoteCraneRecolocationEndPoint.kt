package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteCraneRecolocationEndPoint {

    @Headers("Content-Type: application/json")
    @POST("craneRecolocation")
    fun saveCraneRecolocation(@HeaderMap headers:Map<String, String>, @Body craneRecolocation: CraneRecolocationResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("craneRecolocation")
    fun getCraneRecolocation(): Call<List<CraneRecolocationResponse>>

}