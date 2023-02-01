package com.androidigniter.excelapp.repository

import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.retrofit.*
import com.androidigniter.excelapp.tasks.project.*


import android.app.Application
import android.app.Person

import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
import com.androidigniter.excelapp.tasks.dailyStatus.InsertCraneDailyStatusAsyncTask
import com.androidigniter.excelapp.tasks.dailyStatus.InsertDailyStatusAsyncTask
import com.androidigniter.excelapp.tasks.dailyStatus.UpdateCraneDailyStatusAsyncTask
import com.androidigniter.excelapp.tasks.dailyStatus.UpdateDailyStatusAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertAllPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.UpdatePersonelAsyncTask
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraneDailyStatusRepository(application: Application) {

    private val craneDailyStatusDao: CraneDailyStatusDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        craneDailyStatusDao = companyDatabase.craneDailyStatusDao()
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(dailyStatus:CraneDailyStatusResponse) {
        InsertCraneDailyStatusAsyncTask(craneDailyStatusDao, this).execute(dailyStatus)
    }

    fun syncOneItem(dailyStatus:CraneDailyStatusResponse) {
        val service = RemoteCompanyService.client.create(RemoteDailyStatusEndPoint::class.java)
        val call = service.saveCraneDailyStatus(headers, dailyStatus)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    dailyStatus.is_sync = true
                    UpdateCraneDailyStatusAsyncTask(craneDailyStatusDao).execute(dailyStatus)


                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncDailyStatus() {
        for(dailyStatus: CraneDailyStatusResponse in craneDailyStatusDao.asyncDailyStatus) {
            syncOneItem(dailyStatus)
        }
    }
//    fun downloadPersonel() {
//        val service = RemoteCompanyService.client.create(RemotePersonelEndPoint::class.java)
//        val call = service.getPersonel()
//        call.enqueue(object : Callback<List<PersonelResponse>> {
//            override fun onResponse(call: Call<List<PersonelResponse>>, response: Response<List<PersonelResponse>>) {
//                if (response.isSuccessful && response.code() == 200) {
//                    InsertAllPersonelAsyncTask(personelDao).execute(response.body());
//
//                }
//            }
//            override fun onFailure(call: Call<List<PersonelResponse>>, t: Throwable) {
//                //mCompanyService.getSyncFailureCompanyObject()
//            }
//        })
//    }
}
