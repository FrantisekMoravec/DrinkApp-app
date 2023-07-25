package com.example.drinkapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.drinkapp.domain.model.Ingredient
import com.example.drinkapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientsSharedViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    private val _allIngredients = MutableStateFlow<PagingData<Ingredient>>(PagingData.empty())
    val allIngredients: StateFlow<PagingData<Ingredient>> = _allIngredients

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getAllIngredientsUseCase()
                .cachedIn(viewModelScope)
                .collect(){
                    _allIngredients.value = it
                }
        }
    }
}