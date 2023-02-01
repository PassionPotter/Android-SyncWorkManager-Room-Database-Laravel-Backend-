package com.androidigniter.excelapp.tasks.resourceActivation



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateResourceActivationAsyncTask(private val resourceActivationDao: ResourceActivationDao) : AsyncTask<ResourceActivationResponse, Void, ResourceActivationResponse>() {
    override fun doInBackground(vararg resourceActivation: ResourceActivationResponse): ResourceActivationResponse {
       val projectItem = resourceActivation[0];
        resourceActivationDao.update(projectItem)
        return projectItem;
    }

}

