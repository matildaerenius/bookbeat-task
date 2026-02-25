package com.matildaerenius.bookbeat_task.domain.model

data class Category(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val booksUrl: String?
)