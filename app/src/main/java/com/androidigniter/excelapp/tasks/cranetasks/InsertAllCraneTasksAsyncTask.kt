package com.androidigniter.excelapp.tasks.cranetasks

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.*
import com.androidigniter.excelapp.model.*

class InsertAllCraneTasksAsyncTask(private val taskDao: CraneTaskDao) : AsyncTask<List<CraneTaskResponse>, Void, Void>() {
    override fun doInBackground(vararg taskList: List<CraneTaskResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in taskDao.allTask.orEmpty().withIndex()) {
            val searchResult:Int = taskList[0].indexOf(value);
            if(searchResult == -1) {
                taskDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in taskList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                taskDao.insert(value);
            }
        }

        return null;

    }
}