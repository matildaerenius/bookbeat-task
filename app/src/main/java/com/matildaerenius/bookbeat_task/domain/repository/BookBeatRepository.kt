package com.matildaerenius.bookbeat_task.domain.repository

import com.matildaerenius.bookbeat_task.domain.model.BookPage
import com.matildaerenius.bookbeat_task.domain.model.Category

interface BookBeatRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getBooks(url: String): BookPage
}