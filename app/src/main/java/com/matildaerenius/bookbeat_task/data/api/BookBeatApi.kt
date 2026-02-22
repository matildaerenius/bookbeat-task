package com.matildaerenius.bookbeat_task.data.api

import com.matildaerenius.bookbeat_task.data.model.books.EmbeddedBooks
import com.matildaerenius.bookbeat_task.data.model.categories.EmbeddedCategories
import com.matildaerenius.bookbeat_task.data.model.hal.HalResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface BookBeatApi {

    @GET("api/categories")
    suspend fun getCategories(): HalResponse<EmbeddedCategories>

    @GET
    suspend fun getBooks(@Url url: String): HalResponse<EmbeddedBooks>
}