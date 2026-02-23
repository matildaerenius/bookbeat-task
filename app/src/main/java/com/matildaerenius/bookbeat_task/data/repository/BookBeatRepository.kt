package com.matildaerenius.bookbeat_task.data.repository

import com.matildaerenius.bookbeat_task.data.api.RetrofitClient
import com.matildaerenius.bookbeat_task.data.mapper.toDomain
import com.matildaerenius.bookbeat_task.domain.model.BookPage
import com.matildaerenius.bookbeat_task.domain.model.Category

class BookBeatRepository {

    private val api = RetrofitClient.apiService

    suspend fun getCategories(): List<Category> {
        val response = api.getCategories()
        return response.embedded.categories.map { it.toDomain() }
    }

    suspend fun getBooks(url: String): BookPage {
        val response = api.getBooks(url)
        val mappedBooks = response.embedded.books.map { it.toDomain() }
        return BookPage(
            books = mappedBooks,
            nextUrl = response.links?.next?.href
        )
    }
}