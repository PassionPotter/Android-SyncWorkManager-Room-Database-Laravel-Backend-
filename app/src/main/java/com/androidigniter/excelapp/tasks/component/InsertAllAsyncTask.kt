package com.androidigniter.excelapp.tasks.company

import android.os.AsyncTask
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.ComponentDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.ComponentResponse


class InsertAllComponentAsyncTask(private val componentDao: ComponentDao) : AsyncTask<List<ComponentResponse>, Void, Void>() {
    override fun doInBackground(vararg componentList: List<ComponentResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in componentDao.allComponents.orEmpty().withIndex()) {
            val searchResult:Int = componentList[0].indexOf(value);
            if(searchResult == -1) {
                componentDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in componentList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                componentDao.insert(value);
            }
        }

        return null;

    }
}

