package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.ComponentResponse

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET

interface RemoteComponentEndPoint {

    @Headers("Content-Type: application/json")
    @GET("fetchComponent")
    fun fetchComponent(): Call<List<ComponentResponse>>

}