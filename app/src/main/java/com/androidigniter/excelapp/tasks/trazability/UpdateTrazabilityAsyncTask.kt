package com.androidigniter.excelapp.tasks.tasks



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateTrazabilityAsyncTask(private val trazabilityDao: TrazabilityDao) : AsyncTask<TrazabilityResponse, Void, TrazabilityResponse>() {
    override fun doInBackground(vararg inefficiencies: TrazabilityResponse): TrazabilityResponse {
        val projectItem = inefficiencies[0];
        trazabilityDao.update(projectItem)
        return projectItem;
    }

}

