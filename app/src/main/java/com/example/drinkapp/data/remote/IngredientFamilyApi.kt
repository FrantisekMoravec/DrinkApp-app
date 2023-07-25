package com.example.drinkapp.data.remote

import com.example.drinkapp.domain.model.IngredientFamilyApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IngredientFamilyApi {

    @GET("/ingredient/families")
    suspend fun getAllIngredientFamilies(
        @Query("page") page: Int = 1
    ): IngredientFamilyApiResponse

    @GET("/ingredients/search")
    suspend fun searchIngredientFamilies(
        @Query("name") name: String
    ): List<IngredientFamilyApiResponse>

}