package com.androidigniter.excelapp.tasks.tasks



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateInefficienciesAsyncTask(private val inefficienciesDao: InefficienciesDao) : AsyncTask<InefficienciesResponse, Void, InefficienciesResponse>() {
    override fun doInBackground(vararg inefficiencies: InefficienciesResponse): InefficienciesResponse {
        val projectItem = inefficiencies[0];
        inefficienciesDao.update(projectItem)
        return projectItem;
    }

}

