package com.androidigniter.excelapp.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.androidigniter.excelapp.model.SubContractorResponse

@Dao
interface SubContractorDao {

    @get:Query("SELECT *  from subcontractor_table")
    val allSubContractors: List<SubContractorResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subcontractor:SubContractorResponse)

    @Query("Delete from subcontractor_table where id=:id")
    fun deleteById(id:Int)
}

