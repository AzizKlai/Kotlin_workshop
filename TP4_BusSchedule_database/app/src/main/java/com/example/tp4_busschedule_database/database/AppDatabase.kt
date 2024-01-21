package com.example.tp4_busschedule_database.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tp4_busschedule_database.database.dao.ScheduleDAO
import com.example.tp4_busschedule_database.database.entities.Schedule

@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getScheduleDao(): ScheduleDAO
    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (this.instance != null) {
                return this.instance!!
            }
            instance = createDatabase(context)
            return instance!!
        }

        fun createDatabase(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app-database"
            )
                .createFromAsset("db/bus_schedule.db")
                .build();
        }
    }
}