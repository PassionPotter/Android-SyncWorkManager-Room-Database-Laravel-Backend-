package com.androidigniter.excelapp.tasks.tasks


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.daos.TaskDao
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.TaskResponse
import com.androidigniter.excelapp.repository.CraneRepository
import com.androidigniter.excelapp.repository.PersonelRepository
import com.androidigniter.excelapp.repository.ProjectRepository
import com.androidigniter.excelapp.repository.TaskRepository


class InsertTaskAsyncTask(private val taskDao: TaskDao, val taskRepo:TaskRepository) : AsyncTask<TaskResponse, Void, TaskResponse>() {
    override fun doInBackground(vararg task: TaskResponse): TaskResponse {
        val projectItem = task[0];
        var generateID:Long = taskDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(task: TaskResponse) {
        super.onPostExecute(task)
        taskRepo.syncOneItem(task);

    }
}

