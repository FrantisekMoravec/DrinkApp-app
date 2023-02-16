package com.example.drinkapp.domain.model

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val drinks: List<Drink> = emptyList(),
    val lastUpdated: Long? = null
)