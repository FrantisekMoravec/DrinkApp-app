package com.example.drinkapp.domain.use_cases.search_ingredient_families

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.IngredientFamily
import kotlinx.coroutines.flow.Flow

class SearchIngredientFamiliesUseCase (
    private val repository: Repository
){
    operator fun invoke(query: String): Flow<PagingData<IngredientFamily>>{
        return repository.searchIngredientFamilies(query = query)
    }
}