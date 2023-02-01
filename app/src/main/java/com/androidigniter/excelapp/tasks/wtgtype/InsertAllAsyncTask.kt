package com.androidigniter.excelapp.tasks.company

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.WTGTypeDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.WTGTypeResponse


class InsertAllWTGTypeAsyncTask(private val wtgTypeDao: WTGTypeDao) : AsyncTask<List<WTGTypeResponse>, Void, Void>() {
    override fun doInBackground(vararg wtgTypeList: List<WTGTypeResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in wtgTypeDao.allWTGTypes.orEmpty().withIndex()) {
            val searchResult:Int = wtgTypeList[0].indexOf(value);
            if(searchResult == -1) {
                wtgTypeDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in wtgTypeList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                wtgTypeDao.insert(value);
            }
        }
        return null;

    }
}

