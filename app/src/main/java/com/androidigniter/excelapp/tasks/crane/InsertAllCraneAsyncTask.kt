package com.androidigniter.excelapp.tasks.crane

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse

class InsertAllCraneAsyncTask(private val craneDao: CraneDao) : AsyncTask<List<CraneResponse>, Void, Void>() {
    override fun doInBackground(vararg craneList: List<CraneResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in craneDao.allCranes.orEmpty().withIndex()) {
            val searchResult:Int = craneList[0].indexOf(value);
            if(searchResult == -1) {
                craneDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in craneList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                craneDao.insert(value);
            }
        }

        return null;

    }
}