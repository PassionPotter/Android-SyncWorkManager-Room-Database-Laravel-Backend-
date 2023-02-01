package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneRecolocationResponse
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface CraneRecolocationDao {

    @get:Query("SELECT *  from crane_recolocation_table")
    val allCraneRecolocation: List<CraneRecolocationResponse>

    @get:Query("Select * from crane_recolocation_table where is_sync=0")
    val asyncCraneRecolocation:List<CraneRecolocationResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(craneRecolocation:CraneRecolocationResponse):Long

    @Query("Delete from crane_recolocation_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(craneRecolocation: CraneRecolocationResponse)
}