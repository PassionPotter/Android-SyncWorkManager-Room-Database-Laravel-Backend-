package com.androidigniter.excelapp.tasks.craneRecolocation


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*


class InsertCraneRecolocationAsyncTask(private val craneRecolocationDao: CraneRecolocationDao, val craneRecolocationRepo:CraneRecolocationRepository) : AsyncTask<CraneRecolocationResponse, Void, CraneRecolocationResponse>() {
    override fun doInBackground(vararg craneRecolocation: CraneRecolocationResponse): CraneRecolocationResponse {
        val projectItem = craneRecolocation[0];
        var generateID:Long = craneRecolocationDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(craneRecolocation: CraneRecolocationResponse) {
        super.onPostExecute(craneRecolocation)
        craneRecolocationRepo.syncOneItem(craneRecolocation);
    }
}

