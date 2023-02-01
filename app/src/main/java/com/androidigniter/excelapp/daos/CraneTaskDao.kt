package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.*


@Dao
interface CraneTaskDao {

    @get:Query("SELECT *  from crane_task_table")
    val allTask: List<CraneTaskResponse>

    @get:Query("Select * from crane_task_table where is_sync=0")
    val asyncTasks:List<CraneTaskResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task:CraneTaskResponse):Long

    @Query("Delete from crane_task_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(task: CraneTaskResponse)
}