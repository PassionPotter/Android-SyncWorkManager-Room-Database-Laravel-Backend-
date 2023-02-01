package com.androidigniter.excelapp.repository
import android.app.Application

import androidx.lifecycle.LiveData
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.WTGTypeDao
import com.androidigniter.excelapp.model.WTGTypeResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteWTGTypeEndPoint
import com.androidigniter.excelapp.services.IWTGTypeService
import com.androidigniter.excelapp.tasks.company.InsertAllWTGTypeAsyncTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WTGTypeRepository(application: Application) : IWTGTypeService {

    private val wtgTypeDao: WTGTypeDao

    val allWTGTypes: List<WTGTypeResponse>

    init {
        val appDatabase = AppDatabase.getInstance(application)
        wtgTypeDao = appDatabase.wtgTypeDao()
        allWTGTypes = wtgTypeDao.allWTGTypes
    }


    override fun syncWTGType() {
        val service = RemoteCompanyService.client.create(RemoteWTGTypeEndPoint::class.java)
        val call = service.fetchWTGType()
        call.enqueue(object : Callback<List<WTGTypeResponse>> {
            override fun onResponse(call: Call<List<WTGTypeResponse>>, response: Response<List<WTGTypeResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllWTGTypeAsyncTask(wtgTypeDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<WTGTypeResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }




}
