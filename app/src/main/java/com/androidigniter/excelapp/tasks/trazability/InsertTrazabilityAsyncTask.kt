package com.androidigniter.excelapp.tasks.tasks


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*


class InsertTrazabilityAsyncTask(private val trazabilityDao: TrazabilityDao, val trazabilityRepo:TrazabilityRepository) : AsyncTask<TrazabilityResponse, Void, TrazabilityResponse>() {
    override fun doInBackground(vararg trazability: TrazabilityResponse): TrazabilityResponse {
        val projectItem = trazability[0];
        var generateID:Long = trazabilityDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(inefficiencies: TrazabilityResponse) {
        super.onPostExecute(inefficiencies)
        trazabilityRepo.syncOneItem(inefficiencies);

    }
}

