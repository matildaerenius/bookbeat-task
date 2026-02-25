package com.matildaerenius.bookbeat_task.data.mapper

import com.matildaerenius.bookbeat_task.data.model.categories.CategoryDto
import com.matildaerenius.bookbeat_task.domain.model.Category

fun CategoryDto.toDomain(): Category {
    return Category(
        id = this.id,
        title = this.title ?: "Okänd kategori",
        imageUrl = this.image,
        booksUrl = this.links?.get("books")?.href
    )
}