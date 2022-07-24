package com.shoxrux.passenger_route.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoxrux.passenger_route.database.entity.CityEntity
import com.shoxrux.passenger_route.database.entity.RouteEntity
import io.reactivex.Flowable

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCity(cityEntity: CityEntity)


//    @Query("select * from RouteEntity")
//    suspend fun getAllRoute(): List<RouteEntity>

    @Query("select * from CityEntity")
    fun getAllCityDb(): Flowable<List<CityEntity>>

//    @Query("select * from RouteEntity where gorod =:city")
//    fun getAllRoute(city:String): List<RouteEntity>


}