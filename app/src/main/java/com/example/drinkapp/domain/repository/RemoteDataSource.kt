package com.example.drinkapp.domain.repository

import androidx.paging.PagingData
import com.example.drinkapp.domain.model.Drink
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllDrinks(): Flow<PagingData<Drink>>

    fun searchDrinks(): Flow<PagingData<Drink>>

}