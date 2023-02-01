package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.CraneResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteCraneEndPoint {

    @Headers("Content-Type: application/json")
    @POST("crane")
    fun saveCrane(@HeaderMap headers:Map<String, String>, @Body crane: CraneResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchCrane")
    fun getCrane(): Call<List<CraneResponse>>
}