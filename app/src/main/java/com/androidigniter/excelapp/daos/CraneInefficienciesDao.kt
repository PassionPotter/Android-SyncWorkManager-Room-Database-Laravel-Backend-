package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.*


@Dao
interface CraneInefficienciesDao {

    @get:Query("SELECT *  from crane_inefficiencies_table")
    val allInefficiencies: List<CraneInefficienciesResponse>

    @get:Query("Select * from crane_inefficiencies_table where is_sync=0")
    val asyncInefficiencies:List<CraneInefficienciesResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(inefficiencies:CraneInefficienciesResponse):Long

    @Query("Delete from crane_inefficiencies_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(inefficiencies: CraneInefficienciesResponse)
}