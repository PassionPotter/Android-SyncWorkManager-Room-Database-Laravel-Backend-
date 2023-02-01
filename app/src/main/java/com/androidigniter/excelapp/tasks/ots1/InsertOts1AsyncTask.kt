package com.androidigniter.excelapp.tasks.project


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.OTS1Dao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.OTS1Response
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.repository.Ots1Repository
import com.androidigniter.excelapp.repository.ProjectRepository


class InsertOts1AsyncTask(private val otS1Dao: OTS1Dao, val ots1Repository: Ots1Repository) : AsyncTask<OTS1Response, Void, OTS1Response>() {
    override fun doInBackground(vararg ots1: OTS1Response): OTS1Response {
        val projectItem = ots1[0];
        var generateID:Long = otS1Dao.insert(projectItem);

        projectItem.id = generateID.toInt();
        return projectItem;
    }

    override fun onPostExecute(ots1: OTS1Response) {
        super.onPostExecute(ots1)
        ots1Repository.syncOneItem(ots1);

    }
}

