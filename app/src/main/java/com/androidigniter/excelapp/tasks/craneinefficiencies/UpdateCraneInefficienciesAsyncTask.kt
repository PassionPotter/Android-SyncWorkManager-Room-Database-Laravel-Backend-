package com.androidigniter.excelapp.tasks.tasks



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateCraneInefficienciesAsyncTask(private val inefficienciesDao: CraneInefficienciesDao) : AsyncTask<CraneInefficienciesResponse, Void, CraneInefficienciesResponse>() {
    override fun doInBackground(vararg inefficiencies: CraneInefficienciesResponse): CraneInefficienciesResponse {
        val projectItem = inefficiencies[0];
        inefficienciesDao.update(projectItem)
        return projectItem;
    }

}

