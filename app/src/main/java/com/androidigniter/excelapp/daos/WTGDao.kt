package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.ProjectResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface WTGDao {

    @get:Query("SELECT *  from wtg_table")
    val allWTGs: List<WTGResponse>

    @get:Query("Select * from wtg_table where is_sync=0")
    val asyncWTGs:List<WTGResponse>


    @Query("Select * from wtg_table where stringID=:id")
    fun stringWTG(id:String):List<WTGResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wtg:WTGResponse):Long

    @Query("Delete from wtg_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(wtg: WTGResponse)
}