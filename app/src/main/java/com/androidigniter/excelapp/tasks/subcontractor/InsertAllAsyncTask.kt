package com.androidigniter.excelapp.tasks.company

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.SubContractorDao
import com.androidigniter.excelapp.model.SubContractorResponse


class InsertAllSubContractorAsyncTask(private val subContractorDao: SubContractorDao) : AsyncTask<List<SubContractorResponse>, Void, Void>() {
    override fun doInBackground(vararg subcontractorList: List<SubContractorResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in subContractorDao.allSubContractors.orEmpty().withIndex()) {
            val searchResult:Int = subcontractorList[0].indexOf(value);
            if(searchResult == -1) {
                subContractorDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in subcontractorList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {

                subContractorDao.insert(value);
            }
        }

        return null;
    }
}

