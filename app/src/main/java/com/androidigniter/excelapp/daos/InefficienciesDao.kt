package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.*


@Dao
interface InefficienciesDao {

    @get:Query("SELECT *  from inefficiencies_table")
    val allInefficiencies: List<InefficienciesResponse>

    @get:Query("Select * from inefficiencies_table where is_sync=0")
    val asyncInefficiencies:List<InefficienciesResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(inefficiencies:InefficienciesResponse):Long

    @Query("Delete from inefficiencies_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(inefficiencies: InefficienciesResponse)
}