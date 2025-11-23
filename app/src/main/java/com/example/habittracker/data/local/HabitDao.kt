package com.example.habittracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habittracker.data.model.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    fun getAll(): LiveData<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit): Long

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)
}
