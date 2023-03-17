package com.example.drinkapp.presentation.screens.add_drink

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.DrinkMadeByUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
/*
@HiltViewModel
class DrinkMadeByUserSharedViewModel @Inject constructor(
    private val repository: Repository
){

    var id by mutableStateOf("")
    var name by mutableStateOf("")
    var image by mutableStateOf("")
    var description by mutableStateOf("")
    var ingredients by mutableStateOf("")
    var tutorial by mutableStateOf("")

    //val allDrinksMadeByUser: StateFlow

}

 */