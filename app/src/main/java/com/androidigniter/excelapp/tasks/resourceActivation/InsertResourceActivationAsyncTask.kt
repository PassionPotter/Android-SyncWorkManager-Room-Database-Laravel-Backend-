package com.androidigniter.excelapp.tasks.resourceActivation


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.daos.ResourceActivationDao
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.ResourceActivationResponse
import com.androidigniter.excelapp.repository.CraneRepository
import com.androidigniter.excelapp.repository.PersonelRepository
import com.androidigniter.excelapp.repository.ProjectRepository
import com.androidigniter.excelapp.repository.ResourceActivationRepository


class InsertResourceActivationAsyncTask(private val resourceActivationDao: ResourceActivationDao, val resourceActivationRepo:ResourceActivationRepository) : AsyncTask<ResourceActivationResponse, Void, ResourceActivationResponse>() {
    override fun doInBackground(vararg resourceActivation: ResourceActivationResponse): ResourceActivationResponse {
        val projectItem = resourceActivation[0];
        var generateID:Long = resourceActivationDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(resourceActivation: ResourceActivationResponse) {
        super.onPostExecute(resourceActivation)
        resourceActivationRepo.syncOneItem(resourceActivation);
    }
}

