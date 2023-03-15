package com.example.drinkapp.presentation.screens.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.util.Constants.DRINK_DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedDrink: MutableStateFlow<Drink?> = MutableStateFlow(null)
    val selectedDrink: StateFlow<Drink?> = _selectedDrink

    /** při kliknutí na drink ho najdeme přes id v databázi */
    init {
        viewModelScope.launch(Dispatchers.IO){
            val drinkId = savedStateHandle.get<Int>(DRINK_DETAILS_ARGUMENT_KEY)
            _selectedDrink.value = drinkId?.let { useCases.getSelectedDrinkUseCase(drinkId = drinkId) }
            //_selectedDrink.value?.name?.let { Log.d("Drink", it) }
        }
    }

}