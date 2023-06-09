package com.example.drinkapp.presentation.screens.filtered_drinks

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.util.Constants.FILTERED_DRINKS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
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

//    private val _selectedIngredients = MutableStateFlow<List<Ingredient>>(emptyList())
//    val selectedIngredients: StateFlow<List<Ingredient>> = _selectedIngredients

    private val _allLocalDrinks = MutableStateFlow<List<Drink>>(emptyList())
    val allLocalDrinks: StateFlow<List<Drink>> = _allLocalDrinks

    init {
        viewModelScope.launch {
            useCases.getAllLocalDrinksUseCase().collect { drinks ->
                _allLocalDrinks.value = drinks
            }
            //TODO hodit sem nějaký logy a sledovat tok dat
            val ingredients = useCases.getSelectedIngredientsByNameUseCase(ingredientNames = checkedIngredients.value.values as List<String>)

            useCases.getDrinksContainingIngredientsUseCase(
                ingredientNames = ingredients.map { it.name },
                ingredientNamesCount = ingredients.size
            ).collect { filteredDrinks2 ->
                _filteredDrinks.value = filteredDrinks2
            }
            /*
            val ingredientNames = savedStateHandle.get<String>(FILTERED_DRINKS_ARGUMENT_KEY)?.split(",")
            if (ingredientNames != null) {
                val ingredients = useCases.getSelectedIngredientsByNameUseCase(ingredientNames)
                _selectedIngredients.value = ingredients
                updateFilteredDrinks()
            }
            */
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

    fun updateFilteredDrinks() {
        if (checkedIngredients.value.isNotEmpty()) {
            val filtered = allLocalDrinks.value.filter { drink ->
                val drinkIngredientNames = drink.ingredients.map { simplifyName(it) }
                val checkedIngredientNames = checkedIngredients.value.values.map { simplifyName(it) }
                checkedIngredientNames.all { name -> drinkIngredientNames.contains(name) }
            }
            _filteredDrinks.value = PagingData.from(filtered)
        } else {
            Log.d("FAB", "filteredDrinks jsou prázdné")
        }
    }

    fun simplifyName(name: String): String {
        val regex = Regex("(\\s\\(.*\\))")
        val simplifiedName = regex.replace(name, "")
        return simplifiedName.lowercase()
    }
}


/*
    private suspend fun getIngredientsByName(names: List<String>): List<Ingredient> {
        return names.map { names ->
            useCases.getSelectedIngredientsByNameUseCase(names).firstOrNull() ?: Ingredient(id = 0, name = name, image = "", description = "", madeByUser = false)
        }
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
    */