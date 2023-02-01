package com.androidigniter.excelapp.tasks.project


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.repository.CraneRepository
import com.androidigniter.excelapp.repository.ProjectRepository


class InsertCraneAsyncTask(private val craneDao: CraneDao, val craneRepo:CraneRepository) : AsyncTask<CraneResponse, Void, CraneResponse>() {
    override fun doInBackground(vararg crane: CraneResponse): CraneResponse {
        val projectItem = crane[0];
        var generateID:Long = craneDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(crane: CraneResponse) {
        super.onPostExecute(crane)
        craneRepo.syncOneItem(crane);

    }
}

