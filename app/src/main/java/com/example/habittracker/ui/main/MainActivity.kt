package com.example.habittracker.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.databinding.ActivityMainBinding
import com.example.habittracker.data.model.Habit
import com.example.habittracker.ui.add.AddHabitActivity
import com.example.habittracker.ui.adapter.HabitAdapter
import com.example.habittracker.viewmodel.HabitViewModel

class MainActivity : AppCompatActivity(), HabitAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HabitAdapter(mutableListOf(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.habits.observe(this) { list ->
            adapter.updateData(list.toMutableList())
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddHabitActivity::class.java))
        }
    }

    override fun onItemClick(habit: Habit) {
        val intent = Intent(this, com.example.habittracker.ui.detail.HabitDetailActivity::class.java)
        intent.putExtra("habit_id", habit.id)
        startActivity(intent)
    }
}
