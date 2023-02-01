package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface CraneDao {

    @get:Query("SELECT *  from crane_table")
    val allCranes: List<CraneResponse>

    @get:Query("Select * from crane_table where is_sync=0")
    val asyncCranes:List<CraneResponse>

    @Query("Select * from crane_table where stringID=:id")
    fun stringCrane(id:String):List<CraneResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(crane:CraneResponse):Long

    @Query("Delete from crane_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(crane: CraneResponse)
}