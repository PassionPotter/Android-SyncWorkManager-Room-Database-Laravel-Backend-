package com.androidigniter.excelapp.tasks.project


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.daos.WTGDao
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.repository.ProjectRepository
import com.androidigniter.excelapp.repository.WTGRepository


class InsertWTGAsyncTask(private val wtgDao: WTGDao, val wtgRepo:WTGRepository) : AsyncTask<WTGResponse, Void, WTGResponse>() {
    override fun doInBackground(vararg wtg: WTGResponse): WTGResponse {
        val projectItem = wtg[0];
        var generateID:Long = wtgDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(wtg: WTGResponse) {
        super.onPostExecute(wtg)
        wtgRepo.syncOneItem(wtg);

    }
}

