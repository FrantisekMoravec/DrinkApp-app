package com.example.drinkapp.data.remote

import com.example.drinkapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {

    @GET("/drinks")
    suspend fun getAllDrinks(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/drinks/search")
    suspend fun searchDrinks(
        @Query("name") name: String
    ): ApiResponse

}