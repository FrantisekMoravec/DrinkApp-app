package com.example.drinkapp.domain.use_cases.get_all_drinks

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Drink
import kotlinx.coroutines.flow.Flow

class GetAllDrinksUseCase(
    private  val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Drink>>{
        return repository.getAllDrinks()
    }
}