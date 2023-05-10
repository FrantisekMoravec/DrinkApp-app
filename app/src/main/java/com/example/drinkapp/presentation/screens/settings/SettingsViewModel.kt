package com.example.drinkapp.presentation.screens.settings
/*
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: UseCases,
    private val dataStore: DataStore<Preferences>
): ViewModel{
    val themeOptions = listOf("Světlý", "Tmavý", "Podle zařízení")
    var selectedTheme = mutableStateOf("")

    /** Klíč pro uložení vybraného téma v DataStore */
    private val selectedThemeKey = stringPreferencesKey("selected_theme")

    init {
        /** Načteme uložené téma z DataStore při inicializaci ViewModelu */
        viewModelScope.launch {
            selectedTheme.value = dataStore.data.map { preferences ->
                preferences[selectedThemeKey] ?: themeOptions[2]
            }.first()
        }
    }

    /** Uložit vybrané téma do DataStore */
    fun saveSelectedTheme(theme: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[selectedThemeKey] = theme
            }
        }
    }
}
*/