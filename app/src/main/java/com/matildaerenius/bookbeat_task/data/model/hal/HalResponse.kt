package com.matildaerenius.bookbeat_task.data.model.hal

import com.google.gson.annotations.SerializedName

data class HalResponse<T>(
    @SerializedName("_embedded") val embedded: T
)
