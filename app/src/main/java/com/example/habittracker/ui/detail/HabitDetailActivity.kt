package com.example.habittracker.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.habittracker.databinding.ActivityHabitDetailBinding
import com.example.habittracker.viewmodel.HabitViewModel

class HabitDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitDetailBinding
    private val viewModel: HabitViewModel by viewModels()
    private var habitId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        habitId = intent.getLongExtra("habit_id", 0)

        viewModel.habits.observe(this) { list ->
            val habit = list.find { it.id == habitId }
            habit?.let {
                binding.tvTitle.text = it.title
                binding.tvCategory.text = it.category
                binding.tvFrequency.text = "Freq/wk: ${it.frequencyPerWeek}"
            }
        }

        binding.btnDelete.setOnClickListener {
            viewModel.habits.value?.find { it.id == habitId }?.let {
                viewModel.deleteHabit(it)
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
