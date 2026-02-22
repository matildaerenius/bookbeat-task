package com.matildaerenius.bookbeat_task.data.repository

import com.matildaerenius.bookbeat_task.data.api.RetrofitClient
import com.matildaerenius.bookbeat_task.data.mapper.toDomain
import com.matildaerenius.bookbeat_task.domain.model.Book
import com.matildaerenius.bookbeat_task.domain.model.Category

class BookBeatRepository {

    private val api = RetrofitClient.apiService

    suspend fun getCategories(): List<Category> {
        val response = api.getCategories()
        return response.embedded.categories.map { it.toDomain() }
    }

    suspend fun getBooks(url: String): List<Book> {
        val response = api.getBooks(url)
        return response.embedded.books.map { it.toDomain() }
    }
}