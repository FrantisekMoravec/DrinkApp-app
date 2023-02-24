package com.example.drinkapp.data.remote

import com.example.drinkapp.domain.model.DrinkApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/** posílá na server requesty na drinky */
interface DrinkApi {

    @GET("/drinks")
    suspend fun getAllDrinks(
        @Query("page") page: Int = 1
    ): DrinkApiResponse

    @GET("/drinks/search")
    suspend fun searchDrinks(
        @Query("name") name: String
    ): DrinkApiResponse

}