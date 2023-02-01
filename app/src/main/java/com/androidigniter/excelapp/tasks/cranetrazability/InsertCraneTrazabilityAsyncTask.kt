package com.androidigniter.excelapp.tasks.tasks


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*


class InsertCraneTrazabilityAsyncTask(private val trazabilityDao: CraneTrazabilityDao, val trazabilityRepo:CraneTrazabilityRepository) : AsyncTask<CraneTrazabilityResponse, Void, CraneTrazabilityResponse>() {
    override fun doInBackground(vararg trazability: CraneTrazabilityResponse): CraneTrazabilityResponse {
        val projectItem = trazability[0];
        var generateID:Long = trazabilityDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(inefficiencies: CraneTrazabilityResponse) {
        super.onPostExecute(inefficiencies)
        trazabilityRepo.syncOneItem(inefficiencies);

    }
}

