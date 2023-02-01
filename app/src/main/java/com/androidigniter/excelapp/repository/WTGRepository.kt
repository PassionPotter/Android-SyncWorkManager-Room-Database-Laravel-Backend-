package com.androidigniter.excelapp.repository

import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.retrofit.*
import com.androidigniter.excelapp.tasks.project.*


import android.app.Application

import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.tasks.wtg.InsertAllWTGAsyncTask
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WTGRepository(application: Application) {

    private val wtgDao: WTGDao
    val allWTGs:List<WTGResponse>
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        wtgDao = companyDatabase.wtgDao()
        allWTGs = wtgDao.allWTGs
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }


    fun insert(wtg:WTGResponse) {
        InsertWTGAsyncTask(wtgDao, this).execute(wtg)
    }

    fun syncOneItem(wtg:WTGResponse) {
        val service = RemoteCompanyService.client.create(RemoteWTGEndPoint::class.java)
        val call = service.saveWTG(headers, wtg)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    wtg.is_sync = true
                    UpdateWTGAsyncTask(wtgDao).execute(wtg)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
    fun syncWTG() {
        for(wtg: WTGResponse in wtgDao.asyncWTGs) {
            syncOneItem(wtg)
        }
    }
    fun downloadWTGs() {
        val service = RemoteCompanyService.client.create(RemoteWTGEndPoint::class.java)
        val call = service.getWTGs()
        call.enqueue(object : Callback<List<WTGResponse>> {
            override fun onResponse(call: Call<List<WTGResponse>>, response: Response<List<WTGResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllWTGAsyncTask(wtgDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<WTGResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
                val id:Int = 5
            }
        })
    }
    fun getWTGByString(stringID:String):WTGResponse {
        return wtgDao.stringWTG(stringID)[0];
    }
}
