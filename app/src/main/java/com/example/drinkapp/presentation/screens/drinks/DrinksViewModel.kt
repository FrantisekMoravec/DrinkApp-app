package com.example.drinkapp.presentation.screens.drinks

import androidx.lifecycle.ViewModel
import com.example.drinkapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** viewmodel fragmentu s drinky - zajišťuje že data zůstanou načtená i při změně stavu */
@HiltViewModel
class DrinksViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllDrinks = useCases.getAllDrinksUseCase()
}