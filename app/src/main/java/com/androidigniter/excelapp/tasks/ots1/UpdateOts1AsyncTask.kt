package com.androidigniter.excelapp.tasks.project



import android.os.AsyncTask
import com.androidigniter.excelapp.daos.OTS1Dao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.OTS1Response
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.repository.ProjectRepository


class UpdateOts1AsyncTask(private val otS1Dao: OTS1Dao) : AsyncTask<OTS1Response, Void, OTS1Response>() {
    override fun doInBackground(vararg ots1: OTS1Response): OTS1Response {
       val projectItem = ots1[0];
        otS1Dao.update(projectItem)
        return projectItem;
    }

}

