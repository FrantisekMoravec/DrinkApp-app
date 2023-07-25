package com.example.drinkapp.data.paging_source

import android.annotation.SuppressLint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.drinkapp.data.remote.IngredientFamilyApi
import com.example.drinkapp.domain.model.IngredientFamily
import javax.inject.Inject

class SearchIngredientFamiliesSource @Inject constructor(
    private val ingredientFamilyApi: IngredientFamilyApi,
    private val query: String
): PagingSource<Int, IngredientFamily>(){

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IngredientFamily> {
        return try {
            val apiResponse = ingredientFamilyApi.searchIngredientFamilies(name = query)
            //val ingredientFamilies = apiResponse.ingredientFamilies
/*
            if (ingredientFamilies.isNotEmpty()){
                LoadResult.Page(
                    data = ingredientFamilies,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            }else{
                */
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            //}
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, IngredientFamily>): Int? {
        return state.anchorPosition
    }
}