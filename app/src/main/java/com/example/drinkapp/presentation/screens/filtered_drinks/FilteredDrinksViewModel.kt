package com.example.drinkapp.presentation.screens.filtered_drinks

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/** viewmodel fragmentu s drinky - zajišťuje že data zůstanou načtená i při změně stavu */

@HiltViewModel
class FilteredDrinksViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _checkedIngredients = MutableStateFlow<Map<Int, String>>(emptyMap())
    val checkedIngredients: StateFlow<Map<Int, String>> = _checkedIngredients

    private val _filteredDrinks = MutableStateFlow<PagingData<Drink>>(PagingData.empty())
    val filteredDrinks: StateFlow<PagingData<Drink>> = _filteredDrinks

    private val _selectedIngredients = MutableStateFlow<List<Ingredient>>(emptyList())
    val selectedIngredients: StateFlow<List<Ingredient>> = _selectedIngredients

    private val _allLocalDrinks = MutableStateFlow<List<Drink>>(emptyList())
    val allLocalDrinks: StateFlow<List<Drink>> = _allLocalDrinks

    init {
        viewModelScope.launch {
            useCases.getAllLocalDrinksUseCase().collect { drinks ->
                _allLocalDrinks.value = drinks
            }

            val ingredientNames = savedStateHandle.get<String>(FILTERED_DRINKS_ARGUMENT_KEY)?.split(",")
            if (ingredientNames != null) {
                val ingredients = ingredientNames.map { name -> Ingredient(id = 0, name = name, image = "", description = "", madeByUser = false) }
                _selectedIngredients.value = ingredients
                updateFilteredDrinks()
            }
        }
    }

    fun addCheckedIngredient(id: Int, value: String) {
        viewModelScope.launch {
            val updatedCheckedIngredients = _checkedIngredients.value.toMutableMap()
            updatedCheckedIngredients[id] = value
            _checkedIngredients.emit(updatedCheckedIngredients)
        }
    }

    fun removeCheckedIngredient(id: Int) {
        viewModelScope.launch {
            val updatedCheckedIngredients = _checkedIngredients.value.toMutableMap()
            updatedCheckedIngredients.remove(id)
            _checkedIngredients.emit(updatedCheckedIngredients)
        }
    }

    fun updateCheckedIngredients(id: Int, name: String, isChecked: Boolean) {
        viewModelScope.launch {
            _checkedIngredients.value = if (isChecked) {
                _checkedIngredients.value + (id to name)
            } else {
                _checkedIngredients.value.filterKeys { it != id }
            }
        }
    }

    private fun updateFilteredDrinks() {
        val filtered = allLocalDrinks.value.filter { drink ->
            val drinkIngredientNames = drink.ingredients.map { simplifyName(it) }
            val selectedIngredientNames = selectedIngredients.value.map { it.name }.map { simplifyName(it) }
            selectedIngredientNames.all { name -> drinkIngredientNames.contains(name) }
        }
        _filteredDrinks.value = PagingData.from(filtered)
    }

    fun updateSelectedIngredients(ingredient: Ingredient, isChecked: Boolean) {
        viewModelScope.launch {
            _selectedIngredients.value = if (isChecked) {
                _selectedIngredients.value + ingredient
            } else {
                _selectedIngredients.value.filter { it.id != ingredient.id }
            }
            updateFilteredDrinks()
        }
    }

    private fun simplifyName(name: String): String {
        val regex = Regex("(\\s\\(.*\\))")
        val simplifiedName = regex.replace(name, "")
        return simplifiedName.lowercase()
    }
}
