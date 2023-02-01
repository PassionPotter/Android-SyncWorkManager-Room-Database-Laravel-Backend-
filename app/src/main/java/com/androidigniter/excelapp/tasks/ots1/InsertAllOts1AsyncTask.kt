package com.androidigniter.excelapp.tasks.project


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.OTS1Dao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.OTS1Response
import com.androidigniter.excelapp.model.ProjectResponse

class InsertAllOts1AsyncTask(private val otS1Dao: OTS1Dao) : AsyncTask<List<OTS1Response>, Void, Void>() {
    override fun doInBackground(vararg otsList: List<OTS1Response>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in otS1Dao.allOts1Data.orEmpty().withIndex()) {
            val searchResult:Int = otsList[0].indexOf(value);
            if(searchResult == -1) {
                otS1Dao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in otsList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                otS1Dao.insert(value);
            }
        }

        return null;

    }
}