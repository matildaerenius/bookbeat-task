package com.matildaerenius.bookbeat_task.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object CategoryListRoute

@Serializable
data class BookListRoute(val url: String)
