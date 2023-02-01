package com.androidigniter.excelapp.repository
import android.app.Application

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.ComponentDao
import com.androidigniter.excelapp.daos.PhaseDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.ComponentResponse
import com.androidigniter.excelapp.model.PhaseResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyEndPoint
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteComponentEndPoint
import com.androidigniter.excelapp.retrofit.RemotePhaseEndPoint
import com.androidigniter.excelapp.services.ICompanyService
import com.androidigniter.excelapp.tasks.company.InsertAllCompanyAsyncTask
import com.androidigniter.excelapp.tasks.company.InsertAllComponentAsyncTask
import com.androidigniter.excelapp.tasks.company.InsertAllPhaseAsyncTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhaseRepository(application: Application) {

    private val phaseDao: PhaseDao

    val allPhase: List<PhaseResponse>

    init {
        val companyDatabase = AppDatabase.getInstance(application)
        phaseDao = companyDatabase.phaseDao()
        allPhase = phaseDao.allPhase
    }


    fun sync() {
        val service = RemoteCompanyService.client.create(RemotePhaseEndPoint::class.java)
        val call = service.fetchPhase()
        call.enqueue(object : Callback<List<PhaseResponse>> {
            override fun onResponse(call: Call<List<PhaseResponse>>, response: Response<List<PhaseResponse>>) {
                if (response.isSuccessful && response.code() == 200) {

                    InsertAllPhaseAsyncTask(phaseDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<PhaseResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }




}
