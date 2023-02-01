package com.androidigniter.excelapp.tasks.dailyStatus



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateDailyStatusAsyncTask(private val dailyStatusDao: DailyStatusDao) : AsyncTask<DailyStatusResponse, Void, DailyStatusResponse>() {
    override fun doInBackground(vararg dailyStatus: DailyStatusResponse): DailyStatusResponse {
        val projectItem = dailyStatus[0];
        dailyStatusDao.update(projectItem)
        return projectItem;
    }

}

