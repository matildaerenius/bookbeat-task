package com.matildaerenius.bookbeat_task.data.model.books

data class BookDto(
    val id: Int,
    val title: String?,
    val image: String?,
    val author: String?,
    val ebook: Boolean?,
    val audio: Boolean?
)
