package com.androidigniter.excelapp.tasks.cranetasks



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateCraneTaskAsyncTask(private val taskDao: CraneTaskDao) : AsyncTask<CraneTaskResponse, Void, CraneTaskResponse>() {
    override fun doInBackground(vararg task: CraneTaskResponse): CraneTaskResponse {
        val projectItem = task[0];
        taskDao.update(projectItem)
        return projectItem;
    }

}

