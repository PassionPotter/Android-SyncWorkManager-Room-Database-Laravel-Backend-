package com.androidigniter.excelapp.daos

import androidx.room.*
import com.androidigniter.excelapp.model.CraneResponse
import com.androidigniter.excelapp.model.PersonelResponse
import com.androidigniter.excelapp.model.WTGResponse


@Dao
interface PersonelDao {

    @get:Query("SELECT *  from personel_table")
    val allPersonel: List<PersonelResponse>

    @get:Query("Select * from personel_table where is_sync=0")
    val asyncPersonel:List<PersonelResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personel:PersonelResponse):Long

    @Query("Delete from personel_table where id=:id")
    fun deleteById(id:Int)

    @Update
    fun update(personel: PersonelResponse)
}