package com.example.drinkapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.domain.model.Drink
import javax.inject.Inject

/** tato třída upravuje data ze serveru pro paging3 knihovnu */
class SearchDrinksSource @Inject constructor(
    private val drinkApi: DrinkApi,
    private val query: String
): PagingSource<Int, Drink>() {

    /** prochází drinky ze serveru jestli se jméno některého bude shodovat s tím který uživatel hledá */
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
                //TODO tohle možná bude dělat bordel
                //load(params)
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Drink>): Int? {
        return state.anchorPosition
    }
}