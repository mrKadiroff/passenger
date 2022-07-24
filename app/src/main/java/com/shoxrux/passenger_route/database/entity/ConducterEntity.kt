package com.shoxrux.passenger_route.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ConducterEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "ism")
    var name: String? = null

    @ColumnInfo(name = "familiya")
    var surname: String? = null

    @ColumnInfo(name = "gorodaa")
    var shahrcha: String? = null

    @ColumnInfo(name = "login")
    var logincha: String? = null

    @ColumnInfo(name = "password")
    var parol: String? = null

}