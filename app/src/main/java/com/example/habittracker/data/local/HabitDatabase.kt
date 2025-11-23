package com.example.habittracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker.data.model.Habit

@Database(entities = [Habit::class], version = 1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: HabitDatabase? = null

        fun getInstance(context: Context): HabitDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                HabitDatabase::class.java, "habit_database.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
