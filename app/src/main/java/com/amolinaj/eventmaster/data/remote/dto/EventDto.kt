package com.amolinaj.eventmaster.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    val id: Int? = null,
    @SerialName("category_id") val categoryId: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    @SerialName("image_res_name") val imageResName: String? = null
)
