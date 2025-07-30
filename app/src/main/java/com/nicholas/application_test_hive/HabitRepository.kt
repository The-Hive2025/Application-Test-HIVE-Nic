package com.nicholas.application_test_hive

class HabitRepository {
    private val api = RetrofitInstance.api

    suspend fun getHabits() = api.getHabits()
    suspend fun addHabit(habit: Habit) = api.addHabit(habit)
    suspend fun markAsDone(habit: Habit) = api.markHabitAsDone(habit.id, habit)
}
