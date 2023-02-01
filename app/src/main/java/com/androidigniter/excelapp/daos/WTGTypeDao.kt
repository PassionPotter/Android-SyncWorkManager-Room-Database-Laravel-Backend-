package com.androidigniter.excelapp.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.androidigniter.excelapp.model.WTGTypeResponse

@Dao
interface WTGTypeDao {

    @get:Query("SELECT *  from wtgtype_table")
    val allWTGTypes: List<WTGTypeResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wtgType:WTGTypeResponse)

    @Query("Delete from wtgtype_table where id=:id")
    fun deleteById(id:Int)
}

