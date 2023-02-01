package com.androidigniter.excelapp.repository
import android.app.Application

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.ComponentDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.ComponentResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyEndPoint
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteComponentEndPoint
import com.androidigniter.excelapp.services.ICompanyService
import com.androidigniter.excelapp.tasks.company.InsertAllCompanyAsyncTask
import com.androidigniter.excelapp.tasks.company.InsertAllComponentAsyncTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComponentRepository(application: Application) {

    private val componentDao: ComponentDao

    val allComponents: List<ComponentResponse>

    init {
        val companyDatabase = AppDatabase.getInstance(application)
        componentDao = companyDatabase.componentDao()
        allComponents = componentDao.allComponents
    }


    fun sync() {
        val service = RemoteCompanyService.client.create(RemoteComponentEndPoint::class.java)
        val call = service.fetchComponent()
        call.enqueue(object : Callback<List<ComponentResponse>> {
            override fun onResponse(call: Call<List<ComponentResponse>>, response: Response<List<ComponentResponse>>) {
                if (response.isSuccessful && response.code() == 200) {

                    InsertAllComponentAsyncTask(componentDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<ComponentResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }




}
