package com.androidigniter.excelapp.tasks.craneRecolocation



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateCraneRecolocationAsyncTask(private val craneRecolocationDao: CraneRecolocationDao) : AsyncTask<CraneRecolocationResponse, Void, CraneRecolocationResponse>() {
    override fun doInBackground(vararg craneRecolocation: CraneRecolocationResponse): CraneRecolocationResponse {
       val projectItem = craneRecolocation[0];
        craneRecolocationDao.update(projectItem)
        return projectItem;
    }

}

