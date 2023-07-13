package com.example.drinkapp.presentation.screens.ingredient_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.model.IngredientFamily
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.util.Constants.INGREDIENT_FAMILY_DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientDetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedIngredientFamily: MutableStateFlow<IngredientFamily?> = MutableStateFlow(null)
    val selectedIngredientFamily: StateFlow<IngredientFamily?> = _selectedIngredientFamily

    private val _ingredientsOfIngredientFamily: MutableStateFlow<List<Ingredient>?> = MutableStateFlow(null)
    val ingredientsOfIngredientFamily: StateFlow<List<Ingredient>?> = _ingredientsOfIngredientFamily

    //private val _drinksContainingIngredients: MutableStateFlow<List<Drink>?> = MutableStateFlow(null)
    //val drinksContainingIngredients: StateFlow<List<Drink>?> = _drinksContainingIngredients

    //val drinksContainingIngredients = useCases.getDrinksContainingIngredientsUseCase(ingredientNames = nullableListIngredientToListString(ingredients = ingredientsOfIngredientFamily.value))

    private val _drinksContainingIngredients = MutableStateFlow<PagingData<Drink>>(PagingData.empty())
    val drinksContainingIngredients = _drinksContainingIngredients

    init {
        viewModelScope.launch(Dispatchers.IO){
            val ingredientFamilyId = savedStateHandle.get<Int>(INGREDIENT_FAMILY_DETAILS_ARGUMENT_KEY)
            _selectedIngredientFamily.value = ingredientFamilyId?.let { useCases.getSelectedIngredientFamilyByIdUseCase(ingredientFamilyId = ingredientFamilyId) }

            selectedIngredientFamily.value?.let { useCases.searchIngredientsByIngredientFamilyNamesUseCase(ingredientFamilyName = it.name) }

            useCases.getDrinksContainingIngredientsUseCase(ingredientNames = nullableListIngredientToListString(ingredientsOfIngredientFamily.value)).cachedIn(viewModelScope).collect {
                _drinksContainingIngredients.value = it
            }
            //ingredientsOfIngredientFamily.value?.let { useCases.getDrinksContainingIngredientsUseCase(ingredientNames = it.map { it.name }) }
        }
    }

    private fun nullableListIngredientToListString(ingredients: List<Ingredient>?): List<String>{
        val ingredientNames = mutableListOf<String>()

        ingredients?.forEach { ingredient -> ingredientNames.add(ingredient.name) }

        return ingredientNames
    }

}
