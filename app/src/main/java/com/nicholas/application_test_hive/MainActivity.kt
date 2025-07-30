package com.nicholas.application_test_hive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HabitViewModel
    private lateinit var habitAdapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.habitRecyclerView)
        habitAdapter = HabitAdapter { habit ->
            viewModel.markHabitDone(habit)
        }
        recyclerView.adapter = habitAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe LiveData
        viewModel.habits.observe(this) { habits ->
            habitAdapter.submitList(habits)
        }

        // Fetch habits from API
        viewModel.fetchHabits()

        // Navigate to AddHabitActivity
        findViewById<Button>(R.id.btnAddHabit).setOnClickListener {
            val intent = Intent(this, AddHabitActivity::class.java)
            startActivity(intent)
        }

        // Manual refresh/sync
        findViewById<Button>(R.id.btnSync).setOnClickListener {
            viewModel.fetchHabits()
        }
    }
}