package com.example.drinkapp.presentation.screens.drink_details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.util.Constants.DRINK_DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
    //,
    //downloadedDrinks: StateFlow<PagingData<Drink>>
): ViewModel() {

    private val _selectedDrink: MutableStateFlow<Drink?> = MutableStateFlow(null)
    val selectedDrink: StateFlow<Drink?> = _selectedDrink

    var drinkId: Int? = null

    /** při kliknutí na drink ho najdeme přes id v databázi */
    init {
        viewModelScope.launch(Dispatchers.IO){
            drinkId = savedStateHandle.get<Int>(DRINK_DETAILS_ARGUMENT_KEY)
            Log.d("safe drink", "DrinkDetailsViewModel - drink id(saved state handle): $drinkId)")
            _selectedDrink.value = drinkId?.let { useCases.getSelectedLocalDrinkUseCase(drinkId = drinkId!!) }
            _selectedDrink.value?.let { Log.d("safe drink", "DrinkDetailsViewModel - drink: ${it.name} (id: ${it.drinkId})") }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>){
        _colorPalette.value = colors
    }

}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}