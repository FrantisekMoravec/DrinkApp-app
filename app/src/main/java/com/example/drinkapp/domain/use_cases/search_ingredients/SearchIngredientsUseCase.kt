package com.example.drinkapp.domain.use_cases.search_ingredients

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

class SearchIngredientsUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Ingredient>>{
        return repository.searchIngredients(query = query)
    }
}