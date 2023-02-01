package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteWTGEndPoint {

    @Headers("Content-Type: application/json")
    @POST("wtg")
    fun saveWTG(@HeaderMap headers:Map<String, String>, @Body wtg: WTGResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("wtgs")
    fun getWTGs(): Call<List<WTGResponse>>
}