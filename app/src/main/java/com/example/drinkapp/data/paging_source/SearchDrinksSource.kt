package com.example.drinkapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink
import javax.inject.Inject

class SearchDrinksSource @Inject constructor(
    private val drinkApi: DrinkApi,
    private val query: String
): PagingSource<Int, Drink>() {

    /** načte hledané drinky z api */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Drink> {
        return try {
            val apiResponse = drinkApi.searchDrinks(name = query)
            val drinks = apiResponse.drinks
            if (drinks.isNotEmpty()){
                LoadResult.Page(
                    data = drinks,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            }else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Drink>): Int? {
        return state.anchorPosition
    }
}