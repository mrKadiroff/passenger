package com.shoxrux.passenger_route.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoxrux.passenger_route.database.entity.ConducterEntity
import com.shoxrux.passenger_route.database.entity.RouteEntity
import io.reactivex.Flowable

@Dao
interface ConducterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addConducter(conducterEntity: ConducterEntity)


//    @Query("select * from RouteEntity")
//    suspend fun getAllRoute(): List<RouteEntity>

    @Query("select * from ConducterEntity where gorodaa =:citycha")
    fun getAllConducter(citycha:String): Flowable<List<ConducterEntity>>

    @Query("select * from ConducterEntity where gorodaa =:citycha")
    fun getAllCon(citycha:String): List<ConducterEntity>


}