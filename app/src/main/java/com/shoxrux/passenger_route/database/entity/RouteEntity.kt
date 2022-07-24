package com.shoxrux.passenger_route.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RouteEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "route")
    var marshrut: String? = null

    @ColumnInfo(name = "gorod")
    var shahr: String? = null


}