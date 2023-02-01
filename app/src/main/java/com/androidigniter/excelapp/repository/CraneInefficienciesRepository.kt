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

class CraneInefficienciesRepository(application: Application) {

    private val inefficienciesDao: CraneInefficienciesDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    val allInefficiencies:List<CraneInefficienciesResponse>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        inefficienciesDao = companyDatabase.craneInefficienciesDao()
        session = SessionHandler(application)
        allInefficiencies = inefficienciesDao.allInefficiencies
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(inefficiencies:CraneInefficienciesResponse) {
        InsertCraneInefficienciesAsyncTask(inefficienciesDao, this).execute(inefficiencies)
    }

    fun syncOneItem(inefficiencies:CraneInefficienciesResponse) {
        val service = RemoteCompanyService.client.create(RemoteInefficienciesEndPoint::class.java)
        val call = service.saveCraneInefficiencies(headers, inefficiencies)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    inefficiencies.is_sync = true
                    UpdateCraneInefficienciesAsyncTask(inefficienciesDao).execute(inefficiencies)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncTask() {
        for(inefficiencies: CraneInefficienciesResponse in inefficienciesDao.asyncInefficiencies) {
            syncOneItem(inefficiencies)
        }
    }
    fun downloadTask() {
        val service = RemoteCompanyService.client.create(RemoteInefficienciesEndPoint::class.java)
        val call = service.getCraneInefficiencies()
        call.enqueue(object : Callback<List<CraneInefficienciesResponse>> {
            override fun onResponse(call: Call<List<CraneInefficienciesResponse>>, response: Response<List<CraneInefficienciesResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllCraneInefficienciesAsyncTask(inefficienciesDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<CraneInefficienciesResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
