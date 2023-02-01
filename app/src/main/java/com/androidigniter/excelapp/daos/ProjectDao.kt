package com.androidigniter.excelapp.daos
import androidx.lifecycle.LiveData
import androidx.room.*

import com.androidigniter.excelapp.model.ProjectResponse

@Dao
interface ProjectDao {

    @get:Query("SELECT *  from project_table")
    val allProjects: List<ProjectResponse>

    @get:Query("Select * from project_table where is_sync=0")
    val asyncProjects:List<ProjectResponse>

    @Query("Select * from project_table where stringID=:id")
    fun stringProject(id:String):List<ProjectResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(project:ProjectResponse):Long

    @Query("Delete from project_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(project: ProjectResponse)
}

