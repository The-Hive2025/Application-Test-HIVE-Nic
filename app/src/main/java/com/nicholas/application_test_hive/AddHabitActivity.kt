package com.nicholas.application_test_hive

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddHabitActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var descInput: EditText
    private lateinit var saveButton: Button

    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        nameInput = findViewById(R.id.editHabitName)
        descInput = findViewById(R.id.editHabitDescription)
        saveButton = findViewById(R.id.btnSaveHabit)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val desc = descInput.text.toString().trim()

            if (name.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Use dummy ID for POST, server should assign real one
            val habit = Habit(
                id = 0,
                name = name,
                description = desc,
                lastCompletedDate = null
            )

            viewModel.addHabit(habit) {
                Toast.makeText(this, "Habit added!", Toast.LENGTH_SHORT).show()
                finish() // Go back to main list
            }
        }
    }
}