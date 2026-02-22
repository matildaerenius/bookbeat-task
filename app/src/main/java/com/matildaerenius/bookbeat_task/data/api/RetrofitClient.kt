package com.matildaerenius.bookbeat_task.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8
object RetrofitClient {
    private const val BASE_URL = "https://api.bookbeat.com/"

    val apiService: BookBeatApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookBeatApi::class.java)
    }
}
