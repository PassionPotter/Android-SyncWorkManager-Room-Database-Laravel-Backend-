package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.PhaseResponse

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET

interface RemotePhaseEndPoint {

    @Headers("Content-Type: application/json")
    @GET("fetchPhase")
    fun fetchPhase(): Call<List<PhaseResponse>>

}