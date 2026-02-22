package com.matildaerenius.bookbeat_task.data.model.categories

import com.google.gson.annotations.SerializedName
import com.matildaerenius.bookbeat_task.data.model.hal.HalLink

data class CategoryDto(
    val id: Int,
    val title: String,
    val image: String,
    @SerializedName("_links") val links: Map<String, HalLink>?
)
