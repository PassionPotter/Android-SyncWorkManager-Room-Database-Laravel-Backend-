package com.androidigniter.excelapp.tasks.personel

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse

class InsertAllPersonelAsyncTask(private val personelDao: PersonelDao) : AsyncTask<List<PersonelResponse>, Void, Void>() {
    override fun doInBackground(vararg personelList: List<PersonelResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in personelDao.allPersonel.orEmpty().withIndex()) {
            val searchResult:Int = personelList[0].indexOf(value);
            if(searchResult == -1) {
                personelDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in personelList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                personelDao.insert(value);
            }
        }

        return null;

    }
}