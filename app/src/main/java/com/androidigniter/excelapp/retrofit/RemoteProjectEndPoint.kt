package com.androidigniter.excelapp.retrofit


import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.ProjectResponse
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.*

interface RemoteProjectEndPoint {

    @Headers("Content-Type: application/json")
    @POST("project")
    fun saveProject(@HeaderMap headers:Map<String, String>, @Body project:ProjectResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchProject")
    fun getProject(): Call<List<ProjectResponse>>
}