package com.androidigniter.excelapp.tasks.personel



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdatePersonelAsyncTask(private val personelDao: PersonelDao) : AsyncTask<PersonelResponse, Void, PersonelResponse>() {
    override fun doInBackground(vararg personel: PersonelResponse): PersonelResponse {
        val projectItem = personel[0];
        personelDao.update(projectItem)
        return projectItem;
    }

}

