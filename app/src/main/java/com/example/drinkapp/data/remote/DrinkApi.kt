package com.example.drinkapp.data.remote

import com.example.drinkapp.domain.model.DrinkApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/** toto rozhraní zajišťuje přístup k rest api */
interface DrinkApi {

    /** pošle na server request k získání všech drinků */
    @GET("/drinks")
    suspend fun getAllDrinks(
        @Query("page") page: Int = 1
    ): DrinkApiResponse

    /** pošle na server request na drink podle jména */
    @GET("/drinks/search")
    suspend fun searchDrinks(
        @Query("name") name: String
    ): DrinkApiResponse

}