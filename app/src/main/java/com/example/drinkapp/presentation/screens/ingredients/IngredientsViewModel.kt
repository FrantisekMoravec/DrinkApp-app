package com.example.drinkapp.presentation.screens.ingredients

import androidx.lifecycle.ViewModel
import com.example.drinkapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** viewmodel fragmentu s ingrediencemi - zajišťuje že data zůstanou načtená i při změně stavu */
@HiltViewModel
class IngredientsViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllIngredients = useCases.getAllIngredientsUseCase()

}