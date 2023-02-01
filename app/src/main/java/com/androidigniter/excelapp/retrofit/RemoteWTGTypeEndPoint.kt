package com.androidigniter.excelapp.retrofit


import com.androidigniter.excelapp.model.WTGTypeResponse

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET

interface RemoteWTGTypeEndPoint {

    @Headers("Content-Type: application/json")
    @GET("fetchWTGType")
    fun fetchWTGType(): Call<List<WTGTypeResponse>>

}