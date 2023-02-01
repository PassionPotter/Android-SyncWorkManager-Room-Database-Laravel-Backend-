package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.*


@Dao
interface ResourceActivationDao {

    @get:Query("SELECT *  from resource_activation_table")
    val allResourceActivation: List<ResourceActivationResponse>

    @get:Query("Select * from resource_activation_table where is_sync=0")
    val asyncResourceActivation:List<ResourceActivationResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resourceActivation:ResourceActivationResponse):Long

    @Query("Delete from resource_activation_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(resourceActivation: ResourceActivationResponse)
}