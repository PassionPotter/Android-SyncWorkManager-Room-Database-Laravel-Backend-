package com.androidigniter.excelapp.retrofit

import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemotePersonelEndPoint {

    @Headers("Content-Type: application/json")
    @POST("personel")
    fun savePersonel(@HeaderMap headers:Map<String, String>, @Body personel: PersonelResponse): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("fetchPersonel")
    fun getPersonel(): Call<List<PersonelResponse>>

}