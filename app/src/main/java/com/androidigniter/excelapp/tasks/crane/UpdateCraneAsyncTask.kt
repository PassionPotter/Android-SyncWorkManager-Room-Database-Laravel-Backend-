package com.androidigniter.excelapp.tasks.project



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.daos.WTGDao
import com.androidigniter.excelapp.daos.WTGTypeDao
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateCraneAsyncTask(private val craneDao: CraneDao) : AsyncTask<CraneResponse, Void, CraneResponse>() {
    override fun doInBackground(vararg crane: CraneResponse): CraneResponse {
       val projectItem = crane[0];
        craneDao.update(projectItem)
        return projectItem;
    }

}

