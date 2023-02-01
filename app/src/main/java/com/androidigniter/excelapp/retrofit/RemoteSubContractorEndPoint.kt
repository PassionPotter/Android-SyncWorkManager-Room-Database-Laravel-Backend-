package com.androidigniter.excelapp.retrofit


import com.androidigniter.excelapp.model.SubContractorResponse

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET

interface RemoteSubContractorEndPoint {

    @Headers("Content-Type: application/json")
    @GET("fetchSubContractor")
    fun fetchSubContractor(): Call<List<SubContractorResponse>>

}