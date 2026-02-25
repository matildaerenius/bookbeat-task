package com.matildaerenius.bookbeat_task.domain.model

data class Book(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val author: String,
    val isEbook: Boolean,
    val isAudiobook: Boolean
)