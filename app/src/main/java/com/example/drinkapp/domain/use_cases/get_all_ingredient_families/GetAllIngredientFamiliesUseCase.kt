package com.example.drinkapp.domain.use_cases.get_all_ingredient_families

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.IngredientFamily
import kotlinx.coroutines.flow.Flow

class GetAllIngredientFamiliesUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<IngredientFamily>>{
        return repository.getAllIngredientFamilies()
    }
}