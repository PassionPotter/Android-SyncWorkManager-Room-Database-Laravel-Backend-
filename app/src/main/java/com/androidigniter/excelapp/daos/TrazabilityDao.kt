package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.TrazabilityResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface TrazabilityDao {

    @get:Query("SELECT *  from trazability_table")
    val allTrazability: List<TrazabilityResponse>

    @get:Query("Select * from trazability_table where is_sync=0")
    val asyncTrazability:List<TrazabilityResponse>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trazability:TrazabilityResponse):Long

    @Query("Delete from trazability_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(trazability: TrazabilityResponse)
}