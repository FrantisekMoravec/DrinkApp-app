package com.example.drinkapp.data.paging_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink
import javax.inject.Inject

class SearchDrinksContainingIngredientsSource @Inject constructor(
    private val drinkApi: DrinkApi,
    private val query: List<String>
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
            val apiResponse = drinkApi.searchDrinksByIngredients(ingredients = convertListToString(list = query))
            Log.d("ingredient", "string ingredinecí(SearchDrinksContainingIngredientsSource): ${convertListToString(list = query)}")
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