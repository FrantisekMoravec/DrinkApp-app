package com.example.drinkapp.domain.use_cases.get_all_ingredients

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

class GetAllIngredientsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Ingredient>>{
        return repository.getAllIngredients()
    }
}