package com.example.tp4_busschedule_database.database

import android.app.Application

class BusScheduleApplication : Application() {
    val database: AppDatabase by lazy{
        AppDatabase.getDatabase(this);
    }
}