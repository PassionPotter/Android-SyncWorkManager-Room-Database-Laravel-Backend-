package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteDailyStatusEndPoint {

    @Headers("Content-Type: application/json")
    @POST("daily-status")
    fun saveDailyStatus(@HeaderMap headers:Map<String, String>, @Body dailyStatus: DailyStatusResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetch-daily-status")
    fun getDailyStatus(): Call<List<DailyStatusResponse>>

    @Headers("Content-Type: application/json")
    @POST("crane-daily-status")
    fun saveCraneDailyStatus(@HeaderMap headers:Map<String, String>, @Body dailyStatus: CraneDailyStatusResponse): Call<ResponseBody>

}