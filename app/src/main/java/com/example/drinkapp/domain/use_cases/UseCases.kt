package com.example.drinkapp.domain.use_cases

import com.example.drinkapp.domain.use_cases.get_all_drinks.GetAllDrinksUseCase
import com.example.drinkapp.domain.use_cases.get_all_ingredients.GetAllIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_selected_drink.GetSelectedDrinkUseCase
import com.example.drinkapp.domain.use_cases.search_drinks.SearchDrinksUseCase
import com.example.drinkapp.domain.use_cases.search_ingredients.SearchIngredientsUseCase

data class UseCases (
    val getAllDrinksUseCase: GetAllDrinksUseCase,
    val searchDrinksUseCase: SearchDrinksUseCase,

    val getAllIngredientsUseCase: GetAllIngredientsUseCase,
    val searchIngredientsUseCase: SearchIngredientsUseCase,

    val getSelectedDrinkUseCase: GetSelectedDrinkUseCase
        )