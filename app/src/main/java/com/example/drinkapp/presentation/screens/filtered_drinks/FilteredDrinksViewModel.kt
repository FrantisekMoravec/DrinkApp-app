package com.example.drinkapp.presentation.screens.filtered_drinks

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.util.Constants.FILTERED_DRINKS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** viewmodel fragmentu s drinky - zajišťuje že data zůstanou načtená i při změně stavu */

@HiltViewModel
class FilteredDrinksViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _filteredDrinks = MutableStateFlow<PagingData<Drink>>(PagingData.empty())
    val filteredDrinks: StateFlow<PagingData<Drink>> = _filteredDrinks

    //private val _selectedIngredients = MutableStateFlow<List<Ingredient>>(emptyList())
    //val selectedIngredients: StateFlow<List<Ingredient>> = _selectedIngredients

    private val _selectedIngredients = MutableSharedFlow<List<Ingredient>>(replay = 1)
    val selectedIngredients: SharedFlow<List<Ingredient>> = _selectedIngredients

    init {
        val ingredientNames = savedStateHandle.get<String>(FILTERED_DRINKS_ARGUMENT_KEY)?.split(",")
        if (ingredientNames != null) {
            viewModelScope.launch {
                val ingredients = ingredientNames.map { name -> Ingredient(
                    id = 0,
                    name = simplifyIngredientName(name),
                    image = "",
                    description = "",
                    madeByUser = false
                ) }
                _selectedIngredients.emit(ingredients)

                selectedIngredients.collect { ingredients ->
                    val ingredientNames = ingredients.map { it.name }
                    val simplifiedNames = ingredientNames.map { simplifyIngredientName(it) }
                    useCases.getDrinksContainingIngredientsUseCase(simplifiedNames).collect {
                        _filteredDrinks.value = it
                    }
                }
            }
        }
    }

    /*
    init {
        val ingredientNames = savedStateHandle.get<String>(FILTERED_DRINKS_ARGUMENT_KEY)?.split(",")
        if (ingredientNames != null) {
            viewModelScope.launch {
                selectedIngredients.collect { ingredients ->
                    val ingredientNames = ingredients.map { it.name }
                    val simplifiedNames = ingredientNames.map { simplifyIngredientName(it) }
                    useCases.getDrinksContainingIngredientsUseCase(simplifiedNames).collect {
                        _filteredDrinks.value = it
                    }
                }
            }
        }
    }
*/
    fun updateSelectedIngredients(ingredient: Ingredient, isChecked: Boolean) {
        viewModelScope.launch {
            val updatedIngredients = if (isChecked) {
                selectedIngredients.replayCache.first() + ingredient
            } else {
                selectedIngredients.replayCache.first().filter { it.id != ingredient.id }
            }
            _selectedIngredients.emit(updatedIngredients)
        }
    }

    private fun simplifyIngredientName(ingredientName: String): String {
        val regex = Regex("(\\s\\(.*\\))")
        return regex.replace(ingredientName, "")
    }
}
