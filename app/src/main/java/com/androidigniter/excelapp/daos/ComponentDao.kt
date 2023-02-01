package com.androidigniter.excelapp.daos
import androidx.lifecycle.LiveData
import androidx.room.*

import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.model.ComponentResponse

@Dao
interface ComponentDao {



    @get:Query("SELECT *  from component_table")
    val allComponents: List<ComponentResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(company:ComponentResponse)

    @Query("Delete from component_table where id=:id")
    fun deleteById(id:Int)
}

