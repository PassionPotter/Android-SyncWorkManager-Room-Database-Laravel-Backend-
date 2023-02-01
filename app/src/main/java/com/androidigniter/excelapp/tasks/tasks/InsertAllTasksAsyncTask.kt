package com.androidigniter.excelapp.tasks.tasks

import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.PersonelDao
import com.androidigniter.excelapp.daos.TaskDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.TaskResponse

class InsertAllTasksAsyncTask(private val taskDao: TaskDao) : AsyncTask<List<TaskResponse>, Void, Void>() {
    override fun doInBackground(vararg taskList: List<TaskResponse>): Void? {

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