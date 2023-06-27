package com.example.drinkapp.domain.use_cases.get_ingredient_families_by_name

import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.IngredientFamily

class GetSelectedIngredientFamiliesByName (
    private val repository: Repository
){
    suspend operator fun invoke(ingredientFamilyNames: List<String>): List<IngredientFamily>{
        return repository.getSelectedIngredientFamiliesByName(ingredientFamilyNames = ingredientFamilyNames)
    }
}