package com.androidigniter.excelapp.repository

import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.retrofit.*
import com.androidigniter.excelapp.tasks.project.*


import android.app.Application
import android.app.Person

import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertAllPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.UpdatePersonelAsyncTask
import com.androidigniter.excelapp.tasks.tasks.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraneTrazabilityRepository(application: Application) {

    private val craneTrazabilityDao: CraneTrazabilityDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    val allTrazability:List<CraneTrazabilityResponse>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        craneTrazabilityDao = companyDatabase.craneTrazabilityDao()
        session = SessionHandler(application)
        allTrazability = craneTrazabilityDao.allTrazability
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(trazability:CraneTrazabilityResponse) {
        InsertCraneTrazabilityAsyncTask(craneTrazabilityDao, this).execute(trazability)
    }

    fun syncOneItem(trazability:CraneTrazabilityResponse) {

        val service = RemoteCompanyService.client.create(RemoteTrazabilityEndPoint::class.java)
        val call = service.saveCraneTrazability(headers, trazability)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    trazability.is_sync = true
                    UpdateCraneTrazabilityAsyncTask(craneTrazabilityDao).execute(trazability)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncTask() {
        for(trazability: CraneTrazabilityResponse in craneTrazabilityDao.asyncTrazability) {
            syncOneItem(trazability)
        }
    }
    fun downloadTask() {
        val service = RemoteCompanyService.client.create(RemoteTrazabilityEndPoint::class.java)
        val call = service.getCraneTrazability()
        call.enqueue(object : Callback<List<CraneTrazabilityResponse>> {
            override fun onResponse(call: Call<List<CraneTrazabilityResponse>>, response: Response<List<CraneTrazabilityResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllCraneTrazabilityAsyncTask(craneTrazabilityDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<CraneTrazabilityResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
