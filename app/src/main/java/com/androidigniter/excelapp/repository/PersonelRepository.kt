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
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertAllPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.UpdatePersonelAsyncTask
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonelRepository(application: Application) {

    private val personelDao: PersonelDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        personelDao = companyDatabase.personelDao()
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(personel:PersonelResponse) {
        InsertPersonelAsyncTask(personelDao, this).execute(personel)
    }

    fun syncOneItem(personel:PersonelResponse) {
        val service = RemoteCompanyService.client.create(RemotePersonelEndPoint::class.java)
        val call = service.savePersonel(headers, personel)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    personel.is_sync = true
                    UpdatePersonelAsyncTask(personelDao).execute(personel)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncPersonel() {
        for(personel: PersonelResponse in personelDao.asyncPersonel) {
            syncOneItem(personel)
        }
    }
    fun downloadPersonel() {
        val service = RemoteCompanyService.client.create(RemotePersonelEndPoint::class.java)
        val call = service.getPersonel()
        call.enqueue(object : Callback<List<PersonelResponse>> {
            override fun onResponse(call: Call<List<PersonelResponse>>, response: Response<List<PersonelResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllPersonelAsyncTask(personelDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<PersonelResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
