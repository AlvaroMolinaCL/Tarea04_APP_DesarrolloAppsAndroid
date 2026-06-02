package com.amolinaj.eventmaster.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int? = null,
    val name: String,
    val description: String = ""
)
