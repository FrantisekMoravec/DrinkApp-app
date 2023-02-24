package com.example.drinkapp.domain.model

@kotlinx.serialization.Serializable
data class DrinkApiResponse (
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val drinks: List<Drink> = emptyList(),
    val lastUpdated: Long? = null
)