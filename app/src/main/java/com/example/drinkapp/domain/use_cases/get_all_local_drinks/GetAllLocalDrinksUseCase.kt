package com.example.drinkapp.domain.use_cases.get_all_local_drinks

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Drink
import kotlinx.coroutines.flow.Flow

class GetAllLocalDrinksUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<List<Drink>> {
        return repository.getAllLocalDrinks()
    }
}