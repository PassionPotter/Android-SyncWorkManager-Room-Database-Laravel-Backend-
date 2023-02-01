package com.androidigniter.excelapp.repository

import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.retrofit.*
import com.androidigniter.excelapp.tasks.project.*


import android.app.Application
import android.app.Person

import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
import com.androidigniter.excelapp.tasks.cranetasks.InsertAllCraneTasksAsyncTask
import com.androidigniter.excelapp.tasks.cranetasks.InsertCraneTaskAsyncTask
import com.androidigniter.excelapp.tasks.cranetasks.UpdateCraneTaskAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertAllPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.InsertPersonelAsyncTask
import com.androidigniter.excelapp.tasks.personel.UpdatePersonelAsyncTask
import com.androidigniter.excelapp.tasks.tasks.InsertAllTasksAsyncTask
import com.androidigniter.excelapp.tasks.tasks.InsertTaskAsyncTask
import com.androidigniter.excelapp.tasks.tasks.UpdateTaskAsyncTask
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CraneTaskRepository(application: Application) {

    private val taskDao: CraneTaskDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    val allTasks:List<CraneTaskResponse>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        taskDao = companyDatabase.craneTaskDao()
        session = SessionHandler(application)
        allTasks = taskDao.allTask
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(task:CraneTaskResponse) {
        InsertCraneTaskAsyncTask(taskDao, this).execute(task)
    }

    fun syncOneItem(task:CraneTaskResponse) {
        val service = RemoteCompanyService.client.create(RemoteTaskEndPoint::class.java)
        val call = service.saveCraneTask(headers, task)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    task.is_sync = true
                    UpdateCraneTaskAsyncTask(taskDao).execute(task)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncTask() {
        for(task: CraneTaskResponse in taskDao.asyncTasks) {
            syncOneItem(task)
        }
    }
    fun downloadTask() {
        val service = RemoteCompanyService.client.create(RemoteTaskEndPoint::class.java)
        val call = service.getCraneTask()
        call.enqueue(object : Callback<List<CraneTaskResponse>> {
            override fun onResponse(call: Call<List<CraneTaskResponse>>, response: Response<List<CraneTaskResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllCraneTasksAsyncTask(taskDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<CraneTaskResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
