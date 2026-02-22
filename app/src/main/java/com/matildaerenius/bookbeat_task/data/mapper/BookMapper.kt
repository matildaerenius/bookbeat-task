package com.matildaerenius.bookbeat_task.data.mapper

import com.matildaerenius.bookbeat_task.data.model.books.BookDto
import com.matildaerenius.bookbeat_task.domain.model.Book

fun BookDto.toDomain(): Book {
    return Book(
        id = this.id,
        title = this.title,
        imageUrl = this.image,
        author = this.author
    )
}