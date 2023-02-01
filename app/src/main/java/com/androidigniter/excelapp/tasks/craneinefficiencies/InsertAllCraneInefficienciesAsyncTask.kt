package com.androidigniter.excelapp.tasks.tasks

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*

class InsertAllCraneInefficienciesAsyncTask(private val inefficienciesDao: CraneInefficienciesDao) : AsyncTask<List<CraneInefficienciesResponse>, Void, Void>() {
    override fun doInBackground(vararg ineffienciesList: List<CraneInefficienciesResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in inefficienciesDao.allInefficiencies.orEmpty().withIndex()) {
            val searchResult:Int = ineffienciesList[0].indexOf(value);
            if(searchResult == -1) {
                inefficienciesDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in ineffienciesList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                inefficienciesDao.insert(value);
            }
        }

        return null;

    }
}