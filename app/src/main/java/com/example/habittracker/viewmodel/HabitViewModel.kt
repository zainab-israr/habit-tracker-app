package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.habittracker.data.local.HabitDatabase
import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.repository.HabitRepository
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: HabitRepository
    val habits: LiveData<List<Habit>>

    init {
        val dao = HabitDatabase.getInstance(application).habitDao()
        repo = HabitRepository(dao)
        habits = repo.getAll()
    }

    fun addHabit(habit: Habit) = viewModelScope.launch { repo.insert(habit) }
    fun updateHabit(habit: Habit) = viewModelScope.launch { repo.update(habit) }
    fun deleteHabit(habit: Habit) = viewModelScope.launch { repo.delete(habit) }
}
