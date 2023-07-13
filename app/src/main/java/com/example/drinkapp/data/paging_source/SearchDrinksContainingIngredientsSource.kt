package com.example.drinkapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink

class SearchDrinksContainingIngredientsSource (
    private val drinkApi: DrinkApi,
    private val listQuery: List<String>
): PagingSource<Int, Drink>(){
    private val separator ="-"

    private fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list){
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Drink> {
        return try {
            val apiResponse = drinkApi.searchDrinksByIngredients(ingredients = convertListToString(list = listQuery))
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
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Drink>): Int? {
        return state.anchorPosition
    }
}