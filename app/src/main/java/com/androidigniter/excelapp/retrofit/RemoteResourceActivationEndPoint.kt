package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteResourceActivationEndPoint {

    @Headers("Content-Type: application/json")
    @POST("resourceActivation")
    fun saveResourceActivation(@HeaderMap headers:Map<String, String>, @Body resourceActivation: ResourceActivationResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchResourceActivation")
    fun getResourceActivation(): Call<List<ResourceActivationResponse>>


}