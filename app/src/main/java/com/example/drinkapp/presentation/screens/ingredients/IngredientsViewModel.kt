package com.example.drinkapp.presentation.screens.ingredients

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.util.Constants
import com.example.drinkapp.util.Constants.INGREDIENT_DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/** viewmodel fragmentu s ingrediencemi - zajišťuje že data zůstanou načtená i při změně stavu */
@HiltViewModel
class IngredientsViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    //val getAllIngredients = useCases.getAllIngredientsUseCase()
    val getAllIngredientFamilies = useCases.getAllIngredientFamiliesUseCase()
}