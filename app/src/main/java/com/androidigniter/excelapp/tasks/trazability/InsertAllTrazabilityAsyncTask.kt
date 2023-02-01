package com.androidigniter.excelapp.tasks.tasks

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*

class InsertAllTrazabilityAsyncTask(private val trazabilityDao: TrazabilityDao) : AsyncTask<List<TrazabilityResponse>, Void, Void>() {
    override fun doInBackground(vararg trazabilityResponseList: List<TrazabilityResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in trazabilityDao.allTrazability.orEmpty().withIndex()) {
            val searchResult:Int = trazabilityResponseList[0].indexOf(value);
            if(searchResult == -1) {
                trazabilityDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in trazabilityResponseList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                trazabilityDao.insert(value);
            }
        }

        return null;

    }
}