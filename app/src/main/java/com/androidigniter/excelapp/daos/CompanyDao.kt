package com.androidigniter.excelapp.daos
import androidx.lifecycle.LiveData
import androidx.room.*

import com.androidigniter.excelapp.model.CompanyResponse

@Dao
interface CompanyDao {



    @get:Query("SELECT *  from company_table")
    val allCompany: List<CompanyResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(company:CompanyResponse)

    @Query("Delete from company_table where id=:id")
    fun deleteById(id:Int)
}

