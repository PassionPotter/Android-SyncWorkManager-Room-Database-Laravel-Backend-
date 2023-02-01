package com.androidigniter.excelapp.tasks.wtg


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.daos.WTGDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse

class InsertAllWTGAsyncTask(private val wtgDao: WTGDao) : AsyncTask<List<WTGResponse>, Void, Void>() {
    override fun doInBackground(vararg wtgList: List<WTGResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in wtgDao.allWTGs.orEmpty().withIndex()) {
            val searchResult:Int = wtgList[0].indexOf(value);
            if(searchResult == -1) {
                wtgDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in wtgList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                wtgDao.insert(value);
            }
        }

        return null;

    }
}