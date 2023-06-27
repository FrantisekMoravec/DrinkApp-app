package com.example.drinkapp.domain.use_cases.get_drinks_containing_ingredients

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Drink
import kotlinx.coroutines.flow.Flow

class GetDrinksContainingIngredientsUseCase (
    private val repository: Repository
){
    operator fun invoke(ingredientFamilyNames: List<String>, ingredientFamilyNamesCount: Int): Flow<PagingData<Drink>> {
        return repository.getDrinksContainingIngredients(ingredientFamilyNames = ingredientFamilyNames, ingredientFamilyNamesCount = ingredientFamilyNamesCount)
    }
}