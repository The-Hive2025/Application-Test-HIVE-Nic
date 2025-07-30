package com.nicholas.application_test_hive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(
    private val onMarkDoneClicked: (Habit) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(HabitDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val habitName = itemView.findViewById<TextView>(R.id.textHabitName)
        private val habitDescription = itemView.findViewById<TextView>(R.id.textHabitDescription)
        private val lastCompleted = itemView.findViewById<TextView>(R.id.textLastCompleted)
        private val btnMarkDone = itemView.findViewById<Button>(R.id.btnMarkDone)

        fun bind(habit: Habit) {
            habitName.text = habit.name
            habitDescription.text = habit.description

            lastCompleted.text = if (habit.lastCompletedDate.isNullOrEmpty()) {
                "Last Completed: Never"
            } else {
                "Last Completed: ${habit.lastCompletedDate}"
            }

            btnMarkDone.setOnClickListener {
                onMarkDoneClicked(habit)
            }
        }
    }
}

class HabitDiffCallback : DiffUtil.ItemCallback<Habit>() {
    override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean =
        oldItem == newItem
}
