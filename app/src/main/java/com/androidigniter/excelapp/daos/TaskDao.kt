package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.TaskResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface TaskDao {

    @get:Query("SELECT *  from task_table")
    val allTask: List<TaskResponse>

    @get:Query("Select * from task_table where is_sync=0")
    val asyncTasks:List<TaskResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task:TaskResponse):Long

    @Query("Delete from task_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(task: TaskResponse)
}