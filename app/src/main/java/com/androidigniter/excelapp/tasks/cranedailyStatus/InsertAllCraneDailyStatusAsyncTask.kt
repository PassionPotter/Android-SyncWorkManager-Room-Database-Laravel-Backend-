package com.androidigniter.excelapp.tasks.dailyStatus

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*

class InsertAllCraneDailyStatusAsyncTask(private val craneDailyStatusDao: CraneDailyStatusDao) : AsyncTask<List<CraneDailyStatusResponse>, Void, Void>() {
    override fun doInBackground(vararg dailyStatusList: List<CraneDailyStatusResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in craneDailyStatusDao.allDailyStatus.orEmpty().withIndex()) {
            val searchResult:Int = dailyStatusList[0].indexOf(value);
            if(searchResult == -1) {
                craneDailyStatusDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in dailyStatusList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                craneDailyStatusDao.insert(value);
            }
        }

        return null;

    }
}