package com.androidigniter.excelapp.repository

import android.app.Application

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyEndPoint
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteCraneEndPoint
import com.androidigniter.excelapp.retrofit.RemoteProjectEndPoint
import com.androidigniter.excelapp.services.ICompanyService
import com.androidigniter.excelapp.services.IProjectService
import com.androidigniter.excelapp.tasks.company.InsertAllCompanyAsyncTask
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
import com.androidigniter.excelapp.tasks.project.InsertCraneAsyncTask
import com.androidigniter.excelapp.tasks.project.InsertProjectAsyncTask
import com.androidigniter.excelapp.tasks.project.UpdateCraneAsyncTask
import com.androidigniter.excelapp.tasks.project.UpdateProjectAsyncTask
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraneRepository(application: Application) {

    private val craneDao: CraneDao

    private val session:SessionHandler


    val allCranes: List<CraneResponse>
    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)

        craneDao = companyDatabase.craneDao()
        allCranes = craneDao.allCranes
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }


    fun insert(crane:CraneResponse) {
        InsertCraneAsyncTask(craneDao, this).execute(crane)
    }

    fun syncOneItem(crane:CraneResponse) {
        val service = RemoteCompanyService.client.create(RemoteCraneEndPoint::class.java)
        val call = service.saveCrane(headers, crane)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    crane.is_sync = true
                    UpdateCraneAsyncTask(craneDao).execute(crane)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncCrane() {
        for(crane:CraneResponse in craneDao.asyncCranes) {
            syncOneItem(crane)
        }
    }
    fun downloadCranes() {
        val service = RemoteCompanyService.client.create(RemoteCraneEndPoint::class.java)
        val call = service.getCrane()
        call.enqueue(object : Callback<List<CraneResponse>> {
            override fun onResponse(call: Call<List<CraneResponse>>, response: Response<List<CraneResponse>>) {
                if (response.isSuccessful && response.code() == 200) {

                    InsertAllCraneAsyncTask(craneDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<CraneResponse>>, t: Throwable) {
                val I: Int = 20
            }
        })
    }
    fun getCraneByString(stringID:String): CraneResponse {
        return craneDao.stringCrane(stringID)[0];
    }
}
