package com.matildaerenius.bookbeat_task.data.model.hal

data class HalLink(
    val href: String,
    val method: String?,
    val title: String?
)