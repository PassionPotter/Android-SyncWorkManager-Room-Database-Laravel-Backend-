package com.androidigniter.excelapp.retrofit


import com.androidigniter.excelapp.utils.constants
import com.google.gson.GsonBuilder

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteCompanyService {

    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {


            val httpClient = OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build()

            val gson = GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create()

            retrofit = Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(constants.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit!!
        }


}
