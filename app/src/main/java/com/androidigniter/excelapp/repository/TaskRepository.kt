package com.androidigniter.excelapp.repository

import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.retrofit.*
import com.androidigniter.excelapp.tasks.project.*


import android.app.Application
import android.app.Person

import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.TaskResponse
import com.androidigniter.excelapp.tasks.crane.InsertAllCraneAsyncTask
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

class TaskRepository(application: Application) {

    private val taskDao: TaskDao
    private val session:SessionHandler

    private val headers:HashMap<String, String>
    val allTasks:List<TaskResponse>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        taskDao = companyDatabase.taskDao()
        session = SessionHandler(application)
        allTasks = taskDao.allTask
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }

    fun insert(task:TaskResponse) {
        InsertTaskAsyncTask(taskDao, this).execute(task)
    }

    fun syncOneItem(task:TaskResponse) {
        val service = RemoteCompanyService.client.create(RemoteTaskEndPoint::class.java)
        val call = service.saveTask(headers, task)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    task.is_sync = true
                    UpdateTaskAsyncTask(taskDao).execute(task)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }

    fun syncTask() {
        for(task: TaskResponse in taskDao.asyncTasks) {
            syncOneItem(task)
        }
    }
    fun downloadTask() {
        val service = RemoteCompanyService.client.create(RemoteTaskEndPoint::class.java)
        val call = service.getTask()
        call.enqueue(object : Callback<List<TaskResponse>> {
            override fun onResponse(call: Call<List<TaskResponse>>, response: Response<List<TaskResponse>>) {
                if (response.isSuccessful && response.code() == 200) {
                    InsertAllTasksAsyncTask(taskDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<TaskResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
}
