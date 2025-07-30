package com.nicholas.application_test_hive

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class HabitViewModel : ViewModel() {
    private val repo = HabitRepository()

    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> = _habits

    fun fetchHabits() {
        viewModelScope.launch {
            try {
                _habits.value = repo.getHabits()
            } catch (e: Exception) {
                Log.e("HabitVM", "Failed: ${e.message}")
            }
        }
    }

    fun addHabit(habit: Habit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repo.addHabit(habit)
                fetchHabits()
                onSuccess()
            } catch (e: Exception) {
                Log.e("AddHabit", "Failed: ${e.message}")
            }
        }
    }

    fun markHabitDone(habit: Habit) {
        viewModelScope.launch {
            val updatedHabit = habit.copy(
                lastCompletedDate = LocalDate.now().toString()
            )
            repo.markAsDone(updatedHabit)
            fetchHabits()
        }
    }
}
