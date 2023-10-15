package com.example.drinkapp.domain.use_cases

//import com.example.drinkapp.domain.use_cases.get_selected_ingredients.GetSelectedIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_all_ingredient_families.GetAllIngredientFamiliesUseCase
import com.example.drinkapp.domain.use_cases.get_all_ingredients.GetAllIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_all_local_drinks.GetAllLocalDrinksUseCase
import com.example.drinkapp.domain.use_cases.get_all_remote_drinks.GetAllRemoteDrinksUseCase
import com.example.drinkapp.domain.use_cases.get_drinks_containing_ingredients.GetDrinksContainingIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_ingredient_families_by_name.GetSelectedIngredientFamiliesByName
import com.example.drinkapp.domain.use_cases.get_ingredients_by_names.GetSelectedIngredientsByNamesUseCase
import com.example.drinkapp.domain.use_cases.get_selected_drink.GetSelectedLocalDrinkUseCase
import com.example.drinkapp.domain.use_cases.get_selected_ingredient_family.GetSelectedIngredientFamilyByIdUseCase
import com.example.drinkapp.domain.use_cases.search_drinks.SearchDrinksUseCase
import com.example.drinkapp.domain.use_cases.search_ingredient_families.SearchIngredientFamiliesUseCase
import com.example.drinkapp.domain.use_cases.search_ingredients.SearchIngredientsUseCase
import com.example.drinkapp.domain.use_cases.search_ingredients_by_ingredient_family_names.SearchIngredientsByIngredientFamilyNameUseCase

data class UseCases(
    val getAllRemoteDrinksUseCase: GetAllRemoteDrinksUseCase,
    val getAllLocalDrinksUseCase: GetAllLocalDrinksUseCase,
    val searchDrinksUseCase: SearchDrinksUseCase,
    val getSelectedLocalDrinkUseCase: GetSelectedLocalDrinkUseCase,

    val getAllIngredientsUseCase: GetAllIngredientsUseCase,
    val searchIngredientsUseCase: SearchIngredientsUseCase,
    //val getAllIngredientsMadeByUserUseCase: GetAllIngredientsMadeByUserUseCase,
    val getSelectedIngredientsByNamesUseCase: GetSelectedIngredientsByNamesUseCase,

    val getAllIngredientFamiliesUseCase: GetAllIngredientFamiliesUseCase,
    val getSelectedIngredientFamiliesByName: GetSelectedIngredientFamiliesByName,
    val searchIngredientFamiliesUseCase: SearchIngredientFamiliesUseCase,
    val getSelectedIngredientFamilyByIdUseCase: GetSelectedIngredientFamilyByIdUseCase,

    val getDrinksContainingIngredientsUseCase: GetDrinksContainingIngredientsUseCase,
    val searchIngredientsByIngredientFamilyNameUseCase: SearchIngredientsByIngredientFamilyNameUseCase

    //val getSelectedIngredientsUseCase: GetSelectedIngredientsUseCase
)