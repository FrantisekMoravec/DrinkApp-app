package com.example.drinkapp.domain.use_cases.get_selected_drink

import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Drink

class GetSelectedLocalDrinkUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(drinkId: Int): Drink{
        return repository.getSelectedLocalDrink(drinkId = drinkId)
    }
}