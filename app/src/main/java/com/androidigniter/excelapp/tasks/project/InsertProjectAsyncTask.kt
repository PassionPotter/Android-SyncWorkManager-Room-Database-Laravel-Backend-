package com.androidigniter.excelapp.tasks.project


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.repository.ProjectRepository


class InsertProjectAsyncTask(private val projectDao: ProjectDao, val projectRepo:ProjectRepository) : AsyncTask<ProjectResponse, Void, ProjectResponse>() {
    override fun doInBackground(vararg project: ProjectResponse): ProjectResponse {
        val projectItem = project[0];
        var generateID:Long = projectDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(project: ProjectResponse) {
        super.onPostExecute(project)
        projectRepo.syncOneItem(project);

    }
}

