package com.androidigniter.excelapp.tasks.craneRecolocation

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*

class InsertAllCraneRecolocationAsyncTask(private val craneRecolocationDao: CraneRecolocationDao) : AsyncTask<List<CraneRecolocationResponse>, Void, Void>() {
    override fun doInBackground(vararg craneRecolocationList: List<CraneRecolocationResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in craneRecolocationDao.allCraneRecolocation.orEmpty().withIndex()) {
            val searchResult:Int = craneRecolocationList[0].indexOf(value);
            if(searchResult == -1) {
                craneRecolocationDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in craneRecolocationList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                craneRecolocationDao.insert(value);
            }
        }

        return null;

    }
}