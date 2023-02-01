package com.androidigniter.excelapp.tasks.dailyStatus


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*


class InsertCraneDailyStatusAsyncTask(private val craneDailyStatusDao: CraneDailyStatusDao, val dailyStatusRepo:CraneDailyStatusRepository) : AsyncTask<CraneDailyStatusResponse, Void, CraneDailyStatusResponse>() {
    override fun doInBackground(vararg dailyStatus: CraneDailyStatusResponse): CraneDailyStatusResponse {
        val projectItem = dailyStatus[0];
        var generateID:Long = craneDailyStatusDao.insert(projectItem);
        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(response: CraneDailyStatusResponse) {
        super.onPostExecute(response)
        dailyStatusRepo.syncOneItem(response);

    }
}

