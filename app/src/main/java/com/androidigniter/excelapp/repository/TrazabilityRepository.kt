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

class TrazabilityRepository(application: Application) {

    private val trazabilityDao: TrazabilityDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    val allTrazability:List<TrazabilityResponse>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        trazabilityDao = companyDatabase.trazabilityDao()
        session = SessionHandler(application)
        allTrazability = trazabilityDao.allTrazability
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(trazability:TrazabilityResponse) {
        InsertTrazabilityAsyncTask(trazabilityDao, this).execute(trazability)
    }

    fun syncOneItem(trazability:TrazabilityResponse) {

        val service = RemoteCompanyService.client.create(RemoteTrazabilityEndPoint::class.java)
        val call = service.saveTrazability(headers, trazability)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    trazability.is_sync = true
                    UpdateTrazabilityAsyncTask(trazabilityDao).execute(trazability)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncTask() {
        for(trazability: TrazabilityResponse in trazabilityDao.asyncTrazability) {
            syncOneItem(trazability)
        }
    }
    fun downloadTask() {
        val service = RemoteCompanyService.client.create(RemoteTrazabilityEndPoint::class.java)
        val call = service.getTrazability()
        call.enqueue(object : Callback<List<TrazabilityResponse>> {
            override fun onResponse(call: Call<List<TrazabilityResponse>>, response: Response<List<TrazabilityResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllTrazabilityAsyncTask(trazabilityDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<TrazabilityResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
