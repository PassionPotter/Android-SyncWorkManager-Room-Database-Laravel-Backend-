package com.androidigniter.excelapp.tasks.resourceActivation

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.daos.ResourceActivationDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.ResourceActivationResponse

class InsertAllResourceActivationAsyncTask(private val resourceActivationDao: ResourceActivationDao) : AsyncTask<List<ResourceActivationResponse>, Void, Void>() {
    override fun doInBackground(vararg resourceActivationList: List<ResourceActivationResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in resourceActivationDao.allResourceActivation.orEmpty().withIndex()) {
            val searchResult:Int = resourceActivationList[0].indexOf(value);
            if(searchResult == -1) {
                resourceActivationDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in resourceActivationList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                resourceActivationDao.insert(value);
            }
        }

        return null;

    }
}