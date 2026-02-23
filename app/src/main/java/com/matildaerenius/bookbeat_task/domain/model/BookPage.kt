package com.matildaerenius.bookbeat_task.domain.model

data class BookPage(
    val books: List<Book>,
    val nextUrl: String?
)
