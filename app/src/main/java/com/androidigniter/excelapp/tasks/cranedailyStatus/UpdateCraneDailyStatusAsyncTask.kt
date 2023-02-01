package com.androidigniter.excelapp.tasks.dailyStatus



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateCraneDailyStatusAsyncTask(private val dailyStatusDao: CraneDailyStatusDao) : AsyncTask<CraneDailyStatusResponse, Void, CraneDailyStatusResponse>() {
    override fun doInBackground(vararg dailyStatus: CraneDailyStatusResponse): CraneDailyStatusResponse {
        val projectItem = dailyStatus[0];
        dailyStatusDao.update(projectItem)
        return projectItem;
    }

}

