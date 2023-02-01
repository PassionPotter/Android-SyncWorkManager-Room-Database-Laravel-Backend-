package com.androidigniter.excelapp.repository

import android.app.Application

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.daos.AppDatabase
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.retrofit.RemoteCompanyEndPoint
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteProjectEndPoint
import com.androidigniter.excelapp.services.ICompanyService
import com.androidigniter.excelapp.services.IProjectService
import com.androidigniter.excelapp.tasks.company.InsertAllCompanyAsyncTask
import com.androidigniter.excelapp.tasks.project.InsertAllProjectAsyncTask
import com.androidigniter.excelapp.tasks.project.InsertProjectAsyncTask
import com.androidigniter.excelapp.tasks.project.UpdateProjectAsyncTask
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectRepository(application: Application) : IProjectService {

    private val projectDao: ProjectDao

    private val session:SessionHandler
    val allProjects:List<ProjectResponse>
    private val headers:HashMap<String, String>
    init {
        val companyDatabase = AppDatabase.getInstance(application)
        projectDao = companyDatabase.projectDao()
        allProjects = projectDao.allProjects
        session = SessionHandler(application)
        headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
    }


    fun insert(project:ProjectResponse) {
        InsertProjectAsyncTask(projectDao, this).execute(project)
    }

    fun syncOneItem(project:ProjectResponse) {
        val service = RemoteCompanyService.client.create(RemoteProjectEndPoint::class.java)
        val call = service.saveProject(headers, project)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    project.is_sync = true
                    UpdateProjectAsyncTask(projectDao).execute(project)
                    //InsertAllCompanyAsyncTask(companyDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
    fun syncCrane() {
        for(project:ProjectResponse in projectDao.asyncProjects) {
            syncOneItem(project)
        }
    }
    fun downloadProjects() {
        val service = RemoteCompanyService.client.create(RemoteProjectEndPoint::class.java)
        val call = service.getProject()
        call.enqueue(object : Callback<List<ProjectResponse>> {
            override fun onResponse(call: Call<List<ProjectResponse>>, response: Response<List<ProjectResponse>>) {
                if (response.isSuccessful && response.code() == 200) {

                    InsertAllProjectAsyncTask(projectDao).execute(response.body());

                }
            }
            override fun onFailure(call: Call<List<ProjectResponse>>, t: Throwable) {
                //mCompanyService.getSyncFailureCompanyObject()
            }
        })
    }
    fun getProjectByString(stringID:String):ProjectResponse {
        return projectDao.stringProject(stringID)[0];
    }
}
