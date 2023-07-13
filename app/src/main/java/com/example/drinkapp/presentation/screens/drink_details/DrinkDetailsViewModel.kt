package com.example.drinkapp.presentation.screens.drink_details

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

    /** při kliknutí na drink ho najdeme přes id v databázi */
    init {
        viewModelScope.launch(Dispatchers.IO){
            val drinkId = savedStateHandle.get<Int>(DRINK_DETAILS_ARGUMENT_KEY)
            _selectedDrink.value = drinkId?.let { useCases.getSelectedLocalDrinkUseCase(drinkId = drinkId) }
            //_selectedDrink.value?.name?.let { Log.d("Drink", it) }
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