package com.androidigniter.excelapp.retrofit


import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.OTS1Response
import com.androidigniter.excelapp.model.ProjectResponse
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.*

interface RemoteOTS1EndPoint {

    @Headers("Content-Type: application/json")
    @POST("ots1")
    fun saveOTS1(@HeaderMap headers:Map<String, String>, @Body ots1:OTS1Response): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchots1")
    fun getOTS1(): Call<List<OTS1Response>>
}