package com.example.drinkapp.data.remote

import com.example.drinkapp.domain.model.IngredientApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IngredientApi {

    @GET("/ingredients")
    suspend fun getAllIngredients(
        @Query("page") page: Int = 1
    ): IngredientApiResponse

    @GET("/ingredients/search")
    suspend fun searchIngredients(
        @Query("name") name: String
    ): List<IngredientApiResponse>

}