package com.androidigniter.excelapp.repository
import android.app.Application

import androidx.lifecycle.LiveData
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.SubContractorDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.SubContractorResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteSubContractorEndPoint
import com.androidigniter.excelapp.services.ISubContractorService
import com.androidigniter.excelapp.tasks.company.InsertAllSubContractorAsyncTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubContractorRepository(application: Application) : ISubContractorService {

    private val subContractorDao: SubContractorDao

    val allSubContractors: List<SubContractorResponse>

    init {
        val appDatabase = AppDatabase.getInstance(application)
        subContractorDao = appDatabase.subcontractorDao()
        allSubContractors = subContractorDao.allSubContractors
    }


    override fun syncSubContractor() {
        val service = RemoteCompanyService.client.create(RemoteSubContractorEndPoint::class.java)
        val call = service.fetchSubContractor()
        call.enqueue(object : Callback<List<SubContractorResponse>> {
            override fun onResponse(call: Call<List<SubContractorResponse>>, response: Response<List<SubContractorResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllSubContractorAsyncTask(subContractorDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<SubContractorResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }




}
