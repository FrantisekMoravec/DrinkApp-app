package com.example.drinkapp.domain.use_cases.get_ingredients_by_name

import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Ingredient

class GetSelectedIngredientsByNameUseCase (
    private val repository: Repository
){
    suspend operator fun invoke(ingredientNames: List<String>): List<Ingredient>{
        return repository.getSelectedIngredientsByName(ingredientNames = ingredientNames)
    }
}