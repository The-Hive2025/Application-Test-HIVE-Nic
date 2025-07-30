package com.nicholas.application_test_hive

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://688a07914c55d5c73954adda.mockapi.io/api/") // Replace with real or mocky.io endpoint
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: HabitApi = retrofit.create(HabitApi::class.java)
}
