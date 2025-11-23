package com.example.habittracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val category: String,
    val frequencyPerWeek: Int,
    val startDate: Long,
    val streak: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
