package com.androidigniter.excelapp.tasks.dailyStatus


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.DailyStatusDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.DailyStatusResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.repository.CraneRepository
import com.androidigniter.excelapp.repository.DailyStatusRepository
import com.androidigniter.excelapp.repository.PersonelRepository
import com.androidigniter.excelapp.repository.ProjectRepository


class InsertDailyStatusAsyncTask(private val dailyStatusDao: DailyStatusDao, val dailyStatusRepo:DailyStatusRepository) : AsyncTask<DailyStatusResponse, Void, DailyStatusResponse>() {
    override fun doInBackground(vararg dailyStatus: DailyStatusResponse): DailyStatusResponse {
        val projectItem = dailyStatus[0];
        var generateID:Long = dailyStatusDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(personel: DailyStatusResponse) {
        super.onPostExecute(personel)
        dailyStatusRepo.syncOneItem(personel);

    }
}

