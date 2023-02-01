package com.androidigniter.excelapp.tasks.cranetasks

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*


class InsertCraneTaskAsyncTask(private val taskDao: CraneTaskDao, val taskRepo:CraneTaskRepository) : AsyncTask<CraneTaskResponse, Void, CraneTaskResponse>() {
    override fun doInBackground(vararg task: CraneTaskResponse): CraneTaskResponse {
        val projectItem = task[0];
        var generateID:Long = taskDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(task: CraneTaskResponse) {
        super.onPostExecute(task)
        taskRepo.syncOneItem(task);

    }
}

