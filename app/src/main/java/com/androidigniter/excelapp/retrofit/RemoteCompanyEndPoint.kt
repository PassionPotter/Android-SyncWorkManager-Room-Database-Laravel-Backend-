package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.CompanyResponse

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET

interface RemoteCompanyEndPoint {

    @Headers("Content-Type: application/json")
    @GET("fetchCompany")
    fun fetchCompany(): Call<List<CompanyResponse>>

}