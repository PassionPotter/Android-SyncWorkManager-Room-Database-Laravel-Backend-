package com.androidigniter.excelapp.tasks.project



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.daos.WTGDao
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateWTGAsyncTask(private val wtgDao: WTGDao) : AsyncTask<WTGResponse, Void, WTGResponse>() {
    override fun doInBackground(vararg wtg: WTGResponse): WTGResponse {
       val projectItem = wtg[0];
        wtgDao.update(projectItem)
        return projectItem;
    }

}

