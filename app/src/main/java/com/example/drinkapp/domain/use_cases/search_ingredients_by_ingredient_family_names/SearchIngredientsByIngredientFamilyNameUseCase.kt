package com.example.drinkapp.domain.use_cases.search_ingredients_by_ingredient_family_names

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

class SearchIngredientsByIngredientFamilyNameUseCase (
    private val repository: Repository
){
    operator fun invoke(query: String): Flow<PagingData<Ingredient>>{
        return repository.searchIngredientsByIngredientFamilyName(query = query)
    }
}