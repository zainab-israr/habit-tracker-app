package com.example.habittracker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.data.model.Habit
import com.example.habittracker.databinding.ItemHabitBinding

class HabitAdapter(private var items: MutableList<Habit>, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<HabitAdapter.VH>() {

    interface OnItemClickListener { fun onItemClick(habit: Habit) }

    inner class VH(val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) listener.onItemClick(items[pos])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val h = items[position]
        holder.binding.tvTitle.text = h.title
        holder.binding.tvCategory.text = h.category
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: MutableList<Habit>) {
        items = newItems
        notifyDataSetChanged()
    }
}
