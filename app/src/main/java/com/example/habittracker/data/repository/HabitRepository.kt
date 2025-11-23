package com.example.habittracker.data.repository

import com.example.habittracker.data.local.HabitDao
import com.example.habittracker.data.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HabitRepository(private val dao: HabitDao) {
    fun getAll() = dao.getAll()

    suspend fun insert(habit: Habit): Long = withContext(Dispatchers.IO) {
        dao.insert(habit)
    }

    suspend fun update(habit: Habit) = withContext(Dispatchers.IO) {
        dao.update(habit)
    }

    suspend fun delete(habit: Habit) = withContext(Dispatchers.IO) {
        dao.delete(habit)
    }
}
