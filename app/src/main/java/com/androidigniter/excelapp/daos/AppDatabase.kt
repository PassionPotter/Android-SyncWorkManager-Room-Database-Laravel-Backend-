package com.androidigniter.excelapp.daos


import android.app.Person
import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.androidigniter.excelapp.model.*

@Database(entities = [CompanyResponse::class, SubContractorResponse::class, InefficienciesResponse::class,
    WTGTypeResponse::class, ProjectResponse::class, WTGResponse::class, CraneResponse::class, CraneInefficienciesResponse::class,
    PersonelResponse::class, ResourceActivationResponse::class, CraneRecolocationResponse::class, TrazabilityResponse::class, CraneTrazabilityResponse::class,
    TaskResponse::class, DailyStatusResponse::class, CraneTaskResponse::class, CraneDailyStatusResponse::class, PhaseResponse::class, ComponentResponse::class, OTS1Response::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract  fun companyDao():CompanyDao
    abstract  fun wtgTypeDao():WTGTypeDao
    abstract  fun subcontractorDao():SubContractorDao
    abstract  fun projectDao():ProjectDao
    abstract  fun wtgDao():WTGDao
    abstract  fun craneDao():CraneDao
    abstract fun personelDao():PersonelDao
    abstract fun resourceActivationDao():ResourceActivationDao
    abstract fun craneRecolocationDao():CraneRecolocationDao
    abstract fun taskDao():TaskDao
    abstract fun dailyStatusDao():DailyStatusDao
    abstract fun craneDailyStatusDao():CraneDailyStatusDao
    abstract fun craneTaskDao():CraneTaskDao
    abstract fun inefficienciesDao():InefficienciesDao
    abstract fun craneInefficienciesDao():CraneInefficienciesDao
    abstract fun trazabilityDao():TrazabilityDao
    abstract fun craneTrazabilityDao():CraneTrazabilityDao
    abstract fun ots1Dao():OTS1Dao
    abstract fun componentDao():ComponentDao
    abstract fun phaseDao():PhaseDao

    companion object {

        private var instance: AppDatabase? = null

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }

        @Synchronized
        @JvmStatic
        fun getInstance(context: Context): AppDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "galernasoft_db_modified")
                        .allowMainThreadQueries()
                        .addCallback(roomCallback)
                        .build()
            }
            return instance!!

        }
    }




}
