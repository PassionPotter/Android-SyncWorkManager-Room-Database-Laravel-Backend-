package com.androidigniter.excelapp.tasks.personel


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.repository.CraneRepository
import com.androidigniter.excelapp.repository.PersonelRepository
import com.androidigniter.excelapp.repository.ProjectRepository


class InsertPersonelAsyncTask(private val personelDao: PersonelDao, val personalRepo:PersonelRepository) : AsyncTask<PersonelResponse, Void, PersonelResponse>() {
    override fun doInBackground(vararg personel: PersonelResponse): PersonelResponse {
        val projectItem = personel[0];
        var generateID:Long = personelDao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(personel: PersonelResponse) {
        super.onPostExecute(personel)
        personalRepo.syncOneItem(personel);

    }
}

