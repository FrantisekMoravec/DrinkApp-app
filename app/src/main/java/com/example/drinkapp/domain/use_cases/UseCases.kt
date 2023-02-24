package com.example.drinkapp.domain.use_cases

import com.example.drinkapp.domain.use_cases.get_all_drinks.GetAllDrinksUseCase
import com.example.drinkapp.domain.use_cases.search_drinks.SearchDrinksUseCase

data class UseCases (
    val getAllDrinksUseCase: GetAllDrinksUseCase,
    val searchDrinksUseCase: SearchDrinksUseCase
        )