package com.matildaerenius.bookbeat_task.data.repository

import com.matildaerenius.bookbeat_task.data.api.RetrofitClient
import com.matildaerenius.bookbeat_task.data.mapper.toDomain
import com.matildaerenius.bookbeat_task.domain.model.BookPage
import com.matildaerenius.bookbeat_task.domain.model.Category
import com.matildaerenius.bookbeat_task.domain.repository.BookBeatRepository

class BookBeatRepositoryImpl : BookBeatRepository {

    private val api = RetrofitClient.apiService

    override suspend fun getCategories(): List<Category> {
        val response = api.getCategories()
        return response.embedded.categories.map { it.toDomain() }
    }

    override suspend fun getBooks(url: String): BookPage {
        val response = api.getBooks(url)
        val mappedBooks = response.embedded.books.map { it.toDomain() }
        return BookPage(
            books = mappedBooks,
            nextUrl = response.links?.next?.href
        )
    }
}