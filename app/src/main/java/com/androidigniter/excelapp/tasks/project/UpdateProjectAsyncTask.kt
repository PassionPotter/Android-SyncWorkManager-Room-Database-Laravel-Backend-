package com.androidigniter.excelapp.tasks.project



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateProjectAsyncTask(private val projectDao: ProjectDao) : AsyncTask<ProjectResponse, Void, ProjectResponse>() {
    override fun doInBackground(vararg project: ProjectResponse): ProjectResponse {
       val projectItem = project[0];
        projectDao.update(projectItem)
        return projectItem;
    }

}

