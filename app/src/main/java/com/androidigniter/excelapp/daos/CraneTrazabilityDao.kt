package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.CraneTrazabilityResponse
import com.androidigniter.excelapp.model.TrazabilityResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface CraneTrazabilityDao {

    @get:Query("SELECT *  from crane_trazability_table")
    val allTrazability: List<CraneTrazabilityResponse>

    @get:Query("Select * from crane_trazability_table where is_sync=0")
    val asyncTrazability:List<CraneTrazabilityResponse>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trazability:CraneTrazabilityResponse):Long

    @Query("Delete from crane_trazability_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(trazability: CraneTrazabilityResponse)
}