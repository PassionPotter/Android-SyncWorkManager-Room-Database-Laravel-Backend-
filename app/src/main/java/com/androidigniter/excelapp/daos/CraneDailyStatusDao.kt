package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.*


@Dao
interface CraneDailyStatusDao {

    @get:Query("SELECT *  from crane_daily_status_table")
    val allDailyStatus: List<CraneDailyStatusResponse>

    @get:Query("Select * from crane_daily_status_table where is_sync=0")
    val asyncDailyStatus:List<CraneDailyStatusResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dailyStatus:CraneDailyStatusResponse):Long

    @Query("Delete from crane_daily_status_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(dailyStatus: CraneDailyStatusResponse)
}