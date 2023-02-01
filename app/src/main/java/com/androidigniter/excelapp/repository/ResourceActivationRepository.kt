package com.androidigniter.excelapp.repository

import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.retrofit.*
import com.androidigniter.excelapp.tasks.project.*


import android.app.Application
import android.app.Person

import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ResourceActivationResponse
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
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

class ResourceActivationRepository(application: Application) {

    private val resourceActivationDao: ResourceActivationDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        resourceActivationDao = companyDatabase.resourceActivationDao()
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(resourceActivation:ResourceActivationResponse) {
        InsertResourceActivationAsyncTask(resourceActivationDao, this).execute(resourceActivation)
    }

    fun syncOneItem(resourceActivation:ResourceActivationResponse) {
        val service = RemoteCompanyService.client.create(RemoteResourceActivationEndPoint::class.java)
        val call = service.saveResourceActivation(headers, resourceActivation)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    resourceActivation.is_sync = true
                    UpdateResourceActivationAsyncTask(resourceActivationDao).execute(resourceActivation)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncResourceActivation() {
        for(resourceActivation: ResourceActivationResponse in resourceActivationDao.asyncResourceActivation) {
            syncOneItem(resourceActivation)
        }
    }
    fun downloadResourceActivation() {
        val service = RemoteCompanyService.client.create(RemoteResourceActivationEndPoint::class.java)
        val call = service.getResourceActivation()
        call.enqueue(object : Callback<List<ResourceActivationResponse>> {
            override fun onResponse(call: Call<List<ResourceActivationResponse>>, response: Response<List<ResourceActivationResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllResourceActivationAsyncTask(resourceActivationDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<ResourceActivationResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
