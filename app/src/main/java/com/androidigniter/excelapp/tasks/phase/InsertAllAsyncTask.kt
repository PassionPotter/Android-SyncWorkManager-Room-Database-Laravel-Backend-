package com.androidigniter.excelapp.tasks.company

import android.os.AsyncTask
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.PhaseDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.PhaseResponse


class InsertAllPhaseAsyncTask(private val phaseDao: PhaseDao) : AsyncTask<List<PhaseResponse>, Void, Void>() {
    override fun doInBackground(vararg phaseList: List<PhaseResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in phaseDao.allPhase.orEmpty().withIndex()) {
            val searchResult:Int = phaseList[0].indexOf(value);
            if(searchResult == -1) {
                phaseDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in phaseList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                phaseDao.insert(value);
            }
        }

        return null;

    }
}

