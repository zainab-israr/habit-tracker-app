package com.example.habittracker.ui.add

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.habittracker.databinding.ActivityAddHabitBinding
import com.example.habittracker.data.model.Habit
import com.example.habittracker.viewmodel.HabitViewModel

class AddHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddHabitBinding
    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val category = binding.etCategory.text.toString().trim()
            val freq = binding.etFrequency.text.toString().toIntOrNull() ?: 1
            if (title.isEmpty()) {
                Toast.makeText(this, "Enter title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val habit = Habit(title = title, category = category, frequencyPerWeek = freq, startDate = System.currentTimeMillis())
            viewModel.addHabit(habit)
            Toast.makeText(this, "Habit added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
