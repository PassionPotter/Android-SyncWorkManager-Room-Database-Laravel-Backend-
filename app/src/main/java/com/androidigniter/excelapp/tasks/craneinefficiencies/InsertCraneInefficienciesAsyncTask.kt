package com.androidigniter.excelapp.tasks.tasks


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*


class InsertCraneInefficienciesAsyncTask(private val inefficienciesDao: CraneInefficienciesDao, val ineffienciesRepo:CraneInefficienciesRepository) : AsyncTask<CraneInefficienciesResponse, Void, CraneInefficienciesResponse>() {
    override fun doInBackground(vararg inefficiencies: CraneInefficienciesResponse): CraneInefficienciesResponse {
        val projectItem = inefficiencies[0];
        var generateID:Long = inefficienciesDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(inefficiencies: CraneInefficienciesResponse) {
        super.onPostExecute(inefficiencies)
        ineffienciesRepo.syncOneItem(inefficiencies);

    }
}

