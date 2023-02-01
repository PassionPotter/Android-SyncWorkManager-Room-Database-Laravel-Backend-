package com.androidigniter.excelapp.tasks.tasks



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateTaskAsyncTask(private val taskDao: TaskDao) : AsyncTask<TaskResponse, Void, TaskResponse>() {
    override fun doInBackground(vararg task: TaskResponse): TaskResponse {
        val projectItem = task[0];
        taskDao.update(projectItem)
        return projectItem;
    }

}

