package com.example.drinkapp.domain.use_cases.search_drinks

import androidx.paging.PagingData
import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.model.Drink
import kotlinx.coroutines.flow.Flow

class SearchDrinksUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Drink>>{
        return repository.searchDrinks(query = query)
    }
}