package com.nicholas.application_test_hive

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HabitApi {
    @GET("habits")
    suspend fun getHabits(): List<Habit>

    @POST("habits")
    suspend fun addHabit(@Body habit: Habit): Habit

    @PUT("habits/{id}")
    suspend fun markHabitAsDone(
        @Path("id") id: Int,
        @Body habit: Habit
    ): Habit
}
