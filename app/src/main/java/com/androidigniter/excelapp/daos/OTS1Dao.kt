package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.*


@Dao
interface OTS1Dao {

    @get:Query("SELECT *  from ots1_table")
    val allOts1Data: List<OTS1Response>

    @get:Query("Select * from ots1_table where is_sync=0")
    val asyncOts1Data:List<OTS1Response>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ots1:OTS1Response):Long

    @Query("Delete from ots1_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(ots1: OTS1Response)
}