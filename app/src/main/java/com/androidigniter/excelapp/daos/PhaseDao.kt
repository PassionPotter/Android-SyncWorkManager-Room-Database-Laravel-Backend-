package com.androidigniter.excelapp.daos
import androidx.lifecycle.LiveData
import androidx.room.*

import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.PhaseResponse

@Dao
interface PhaseDao {



    @get:Query("SELECT *  from phase_table")
    val allPhase: List<PhaseResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(company:PhaseResponse)

    @Query("Delete from phase_table where id=:id")
    fun deleteById(id:Int)
}

