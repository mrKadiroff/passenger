package com.shoxrux.passenger_route.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shoxrux.passenger_route.database.dao.CityDao
import com.shoxrux.passenger_route.database.dao.ConducterDao
import com.shoxrux.passenger_route.database.dao.RouteDao
import com.shoxrux.passenger_route.database.entity.CityEntity
import com.shoxrux.passenger_route.database.entity.ConducterEntity
import com.shoxrux.passenger_route.database.entity.RouteEntity

@Database(entities = [RouteEntity::class,ConducterEntity::class,CityEntity::class], version = 6)
abstract class AppDatabase : RoomDatabase() {

    abstract fun routeDao(): RouteDao
    abstract fun conducterDao(): ConducterDao
    abstract fun cityDao(): CityDao





    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"pdp.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}