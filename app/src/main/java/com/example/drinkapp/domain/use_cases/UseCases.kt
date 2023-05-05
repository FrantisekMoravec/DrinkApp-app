package com.example.drinkapp.domain.use_cases

import com.example.drinkapp.domain.use_cases.get_all_remote_drinks.GetAllRemoteDrinksUseCase
import com.example.drinkapp.domain.use_cases.get_all_ingredients.GetAllIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_all_ingredients_made_by_user.GetAllIngredientsMadeByUserUseCase
import com.example.drinkapp.domain.use_cases.get_all_local_drinks.GetAllLocalDrinksUseCase
import com.example.drinkapp.domain.use_cases.get_drinks_containing_ingredients.GetDrinksContainingIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_selected_drink.GetSelectedDrinkUseCase
//import com.example.drinkapp.domain.use_cases.get_selected_ingredients.GetSelectedIngredientsUseCase
import com.example.drinkapp.domain.use_cases.search_drinks.SearchDrinksUseCase
import com.example.drinkapp.domain.use_cases.search_ingredients.SearchIngredientsUseCase

data class UseCases (
    val getAllRemoteDrinksUseCase: GetAllRemoteDrinksUseCase,
    val getAllLocalDrinksUseCase: GetAllLocalDrinksUseCase,
    val searchDrinksUseCase: SearchDrinksUseCase,

    val getAllIngredientsUseCase: GetAllIngredientsUseCase,
    val searchIngredientsUseCase: SearchIngredientsUseCase,

    val getSelectedDrinkUseCase: GetSelectedDrinkUseCase,

    val getAllIngredientsMadeByUserUseCase: GetAllIngredientsMadeByUserUseCase,

    val getDrinksContainingIngredientsUseCase: GetDrinksContainingIngredientsUseCase
    //val getSelectedIngredientsUseCase: GetSelectedIngredientsUseCase
)