package com.androidigniter.excelapp.repository
import android.app.Application

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyEndPoint
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.services.ICompanyService
import com.androidigniter.excelapp.tasks.company.InsertAllCompanyAsyncTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyRepository(application: Application) : ICompanyService {

    private val companyDao: CompanyDao

    val allCompanies: List<CompanyResponse>

    init {
        val companyDatabase = AppDatabase.getInstance(application)
        companyDao = companyDatabase.companyDao()
        allCompanies = companyDao.allCompany
    }


    override fun syncCompany() {
        val service = RemoteCompanyService.client.create(RemoteCompanyEndPoint::class.java)
        val call = service.fetchCompany()
        call.enqueue(object : Callback<List<CompanyResponse>> {
            override fun onResponse(call: Call<List<CompanyResponse>>, response: Response<List<CompanyResponse>>) {
                if (response.isSuccessful && response.code() == 200) {

                    InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<CompanyResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }




}
