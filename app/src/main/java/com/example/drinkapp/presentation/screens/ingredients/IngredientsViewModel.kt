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
    val getAllIngredients = useCases.getAllIngredientsUseCase()
}
/*
    val checkedIngredients: MutableState<List<Ingredient>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            val ingredients =
        }
    }
*/
    //val getIngredientsMadeByUser = useCases.getAllIngredientsMadeByUserUseCase()

    //val getAllIngredients = useCases.getAllIngredientsUseCase()
/*
    //val getAllCheckedIngredientsUseCase = useCases.getAllCheckedIngredientsUseCase(checked = true)
    val getAllRemoteDrinks = useCases.getAllDrinksUseCase()
    var checkedIngredients = listOf<Int>()
*/
    //TODO přidat ingrediencím nullable parametr checked
    //TODO udělat v dao request
    //TODO přes usecases vzít všechny checked ingredience
    //TODO dát je do listu
    //init {
        //viewModelScope.launch(Dispatchers.IO){
            //TODO udělat proměnnou list pro každej list ingrediencí v drinku
            //TODO vzít každej string v listu v listu ingrediencí drinku (lowercase) a smazat jeden znak před závorkama, závorky i to co je mezi nima
            //TODO porovnat jestli už neni string v listu
            //TODO porovnat upravenej list ingrediencí v drinku s listem vybranejch - list.containsAll(string) - použít if
            //TODO zobrazit drinky co budou odpovídat
        //}
    //}
    //TODO udělat přechod přes FAB z IngredientScreen do FilteredDrinksByIngredientsScreen