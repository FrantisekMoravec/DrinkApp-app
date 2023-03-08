package com.example.drinkapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.drinkapp.data.remote.IngredientApi
import com.example.drinkapp.domain.model.Ingredient
import javax.inject.Inject

class SearchIngredientsSource @Inject constructor(
    private val ingredientApi: IngredientApi,
    private val query: String
): PagingSource<Int, Ingredient>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Ingredient> {
        return try {
            val apiResponse = ingredientApi.searchIngredients(name = query)
            val ingredients = apiResponse.ingredients
            if (ingredients.isNotEmpty()){
                LoadResult.Page(
                    data = ingredients,
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

    override fun getRefreshKey(state: PagingState<Int, Ingredient>): Int? {
        return state.anchorPosition
    }
}