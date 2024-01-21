package com.example.tp4_busschedule_database.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "stop_name") val stopName : String,
    @ColumnInfo(name = "arrival_time") val arrivalTime : Int
)