package com.androidigniter.excelapp.tasks.tasks



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateCraneTrazabilityAsyncTask(private val trazabilityDao: CraneTrazabilityDao) : AsyncTask<CraneTrazabilityResponse, Void, CraneTrazabilityResponse>() {
    override fun doInBackground(vararg inefficiencies: CraneTrazabilityResponse): CraneTrazabilityResponse {
        val projectItem = inefficiencies[0];
        trazabilityDao.update(projectItem)
        return projectItem;
    }

}

