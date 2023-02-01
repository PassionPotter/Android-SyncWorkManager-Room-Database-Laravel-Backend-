package com.androidigniter.excelapp.tasks.project


import android.os.AsyncTask
import com.androidigniter.excelapp.daos.CompanyDao
import com.androidigniter.excelapp.daos.CraneDao
import com.androidigniter.excelapp.daos.ProjectDao
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.ProjectResponse

class InsertAllProjectAsyncTask(private val projectDao: ProjectDao) : AsyncTask<List<ProjectResponse>, Void, Void>() {
    override fun doInBackground(vararg projectList: List<ProjectResponse>): Void? {

        var flagNew:ArrayList<Int> = arrayListOf();
        for((index, value) in projectDao.allProjects.orEmpty().withIndex()) {
            val searchResult:Int = projectList[0].indexOf(value);
            if(searchResult == -1) {
                projectDao.deleteById(value.id)
            }
            else {
                flagNew.add(searchResult)
            }
        }
        for((index, value) in projectList[0].withIndex()) {
            if(flagNew.indexOf(index) == -1) {
                value.is_sync = true
                projectDao.insert(value);
            }
        }

        return null;

    }
}