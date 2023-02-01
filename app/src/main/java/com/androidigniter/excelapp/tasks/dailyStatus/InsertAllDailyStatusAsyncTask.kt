package com.androidigniter.excelapp.tasks.dailyStatus

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.DailyStatusDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.DailyStatusResponse
import com.androidigniter.excelapp.model.PersonelResponse

class InsertAllDailyStatusAsyncTask(private val dailyStatusDao: DailyStatusDao) : AsyncTask<List<DailyStatusResponse>, Void, Void>() {
    override fun doInBackground(vararg dailyStatusList: List<DailyStatusResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in dailyStatusDao.allDailyStatus.orEmpty().withIndex()) {
            val searchResult:Int = dailyStatusList[0].indexOf(value);
            if(searchResult == -1) {
                dailyStatusDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in dailyStatusList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                dailyStatusDao.insert(value);
            }
        }

        return null;

    }
}