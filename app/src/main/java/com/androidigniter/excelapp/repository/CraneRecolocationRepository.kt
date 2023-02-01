package com.androidigniter.excelapp.repository

import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.retrofit.*
import com.androidigniter.excelapp.tasks.project.*


import android.app.Application
import android.app.Person

import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
import com.androidigniter.excelapp.tasks.craneRecolocation.InsertAllCraneRecolocationAsyncTask
import com.androidigniter.excelapp.tasks.craneRecolocation.InsertCraneRecolocationAsyncTask
import com.androidigniter.excelapp.tasks.craneRecolocation.UpdateCraneRecolocationAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertAllPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.UpdatePersonelAsyncTask
import com.androidigniter.excelapp.tasks.resourceActivation.InsertAllResourceActivationAsyncTask
import com.androidigniter.excelapp.tasks.resourceActivation.InsertResourceActivationAsyncTask
import com.androidigniter.excelapp.tasks.resourceActivation.UpdateResourceActivationAsyncTask
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraneRecolocationRepository(application: Application) {

    private val craneRecolocationDao: CraneRecolocationDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        craneRecolocationDao = companyDatabase.craneRecolocationDao()
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(craneRecolocation:CraneRecolocationResponse) {
        InsertCraneRecolocationAsyncTask(craneRecolocationDao, this).execute(craneRecolocation)
    }

    fun syncOneItem(craneRecolocation:CraneRecolocationResponse) {
        val service = RemoteCompanyService.client.create(RemoteCraneRecolocationEndPoint::class.java)
        val call = service.saveCraneRecolocation(headers, craneRecolocation)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    craneRecolocation.is_sync = true
                    UpdateCraneRecolocationAsyncTask(craneRecolocationDao).execute(craneRecolocation)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncCraneRecolocation() {
        for(craneRecolocation: CraneRecolocationResponse in craneRecolocationDao.asyncCraneRecolocation) {
            syncOneItem(craneRecolocation)
        }
    }
    fun downloadCraneRecolocation() {
        val service = RemoteCompanyService.client.create(RemoteCraneRecolocationEndPoint::class.java)
        val call = service.getCraneRecolocation()
        call.enqueue(object : Callback<List<CraneRecolocationResponse>> {
            override fun onResponse(call: Call<List<CraneRecolocationResponse>>, response: Response<List<CraneRecolocationResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllCraneRecolocationAsyncTask(craneRecolocationDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<CraneRecolocationResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
