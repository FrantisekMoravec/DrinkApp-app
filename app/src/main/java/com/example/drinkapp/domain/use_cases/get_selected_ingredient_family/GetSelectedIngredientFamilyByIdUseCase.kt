package com.example.drinkapp.domain.use_cases.get_selected_ingredient_family

import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.IngredientFamily

class GetSelectedIngredientFamilyByIdUseCase (
    private val repository: Repository
){
    suspend operator fun invoke(ingredientFamilyId: Int): IngredientFamily{
        return repository.getSelectedIngredientFamilyById(ingredientFamilyId = ingredientFamilyId)
    }
}