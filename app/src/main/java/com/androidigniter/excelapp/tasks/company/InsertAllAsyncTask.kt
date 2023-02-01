package com.androidigniter.excelapp.tasks.company

import android.os.AsyncTask
import androidx.lifecycle.Observer
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.model.CompanyResponse


class InsertAllCompanyAsyncTask(private val companyDao: CompanyDao) : AsyncTask<List<CompanyResponse>, Void, Void>() {
    override fun doInBackground(vararg companyList: List<CompanyResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in companyDao.allCompany.orEmpty().withIndex()) {
            val searchResult:Int = companyList[0].indexOf(value);
            if(searchResult == -1) {
                companyDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in companyList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                companyDao.insert(value);
            }
        }

        return null;

    }
}

