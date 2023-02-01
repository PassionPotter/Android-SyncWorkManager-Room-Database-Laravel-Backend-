package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.DailyStatusResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface DailyStatusDao {

    @get:Query("SELECT *  from daily_status_table")
    val allDailyStatus: List<DailyStatusResponse>

    @get:Query("Select * from daily_status_table where is_sync=0")
    val asyncDailyStatus:List<DailyStatusResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dailyStatus:DailyStatusResponse):Long

    @Query("Delete from daily_status_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(dailyStatus: DailyStatusResponse)
}