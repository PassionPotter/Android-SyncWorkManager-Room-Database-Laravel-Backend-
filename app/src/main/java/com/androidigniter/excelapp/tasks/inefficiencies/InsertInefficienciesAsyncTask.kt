package com.androidigniter.excelapp.tasks.tasks


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*


class InsertInefficienciesAsyncTask(private val inefficienciesDao: InefficienciesDao, val ineffienciesRepo:InefficienciesRepository) : AsyncTask<InefficienciesResponse, Void, InefficienciesResponse>() {
    override fun doInBackground(vararg inefficiencies: InefficienciesResponse): InefficienciesResponse {
        val projectItem = inefficiencies[0];
        var generateID:Long = inefficienciesDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(inefficiencies: InefficienciesResponse) {
        super.onPostExecute(inefficiencies)
        ineffienciesRepo.syncOneItem(inefficiencies);

    }
}

