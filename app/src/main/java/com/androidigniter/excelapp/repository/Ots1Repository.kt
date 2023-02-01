package com.androidigniter.excelapp.repository

import android.app.Application

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.OTS1Dao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.OTS1Response
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyEndPoint
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteOTS1EndPoint
import com.androidigniter.excelapp.retrofit.RemoteProjectEndPoint
import com.androidigniter.excelapp.services.ICompanyService
import com.androidigniter.excelapp.services.IProjectService
import com.androidigniter.excelapp.tasks.company.InsertAllCompanyAsyncTask
import com.androidigniter.excelapp.tasks.project.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ots1Repository(application: Application) : IProjectService {

    private val ots1Dao: OTS1Dao

    private val session:SessionHandler
    val allOts:List<OTS1Response>
    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        ots1Dao = companyDatabase.ots1Dao()
        allOts = ots1Dao.allOts1Data
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }


    fun insert(ots1:OTS1Response) {
        InsertOts1AsyncTask(ots1Dao, this).execute(ots1)
    }

    fun syncOneItem(ots1:OTS1Response) {
        val service = RemoteCompanyService.client.create(RemoteOTS1EndPoint::class.java)
        val call = service.saveOTS1(headers, ots1)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    ots1.is_sync = true
                    UpdateOts1AsyncTask(ots1Dao).execute(ots1)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
    fun sync() {
        for(ots1:OTS1Response in ots1Dao.asyncOts1Data) {
            syncOneItem(ots1)
        }
    }
    fun download() {
        val service = RemoteCompanyService.client.create(RemoteOTS1EndPoint::class.java)
        val call = service.getOTS1()
        call.enqueue(object : Callback<List<OTS1Response>> {
            override fun onResponse(call: Call<List<OTS1Response>>, response: Response<List<OTS1Response>>) {
                if (response.isSuccessful && response.code() == 200) {

                    InsertAllOts1AsyncTask(ots1Dao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<OTS1Response>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
//    fun getProjectByString(stringID:String):OTS1Response {
//        return projectDao.stringProject(stringID)[0];
//    }
}
