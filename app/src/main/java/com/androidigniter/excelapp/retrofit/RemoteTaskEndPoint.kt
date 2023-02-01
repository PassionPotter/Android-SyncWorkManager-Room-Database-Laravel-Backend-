package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteTaskEndPoint {

    @Headers("Content-Type: application/json")
    @POST("task")
    fun saveTask(@HeaderMap headers:Map<String, String>, @Body task: TaskResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchTask")
    fun getTask(): Call<List<TaskResponse>>

    @Headers("Content-Type: application/json")
    @POST("crane-task")
    fun saveCraneTask(@HeaderMap headers:Map<String, String>, @Body task: CraneTaskResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchCraneTask")
    fun getCraneTask(): Call<List<CraneTaskResponse>>

}