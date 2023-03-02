package com.example.drinkapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.drinkapp.data.local.DrinkDatabase
import com.example.drinkapp.data.remote.DrinkApi
import com.example.drinkapp.data.repository.RemoteDataSourceImpl
import com.example.drinkapp.domain.repository.RemoteDataSource
import com.example.drinkapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/** tento objekt poskytuje třídy knihovny retrofit pro komunikaci se serverem */

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /** pokud aplikace nenaváže spojení se serverem do 15 sekund přestane se zobrazovat efekt načítání a zobrazí se chyba připojení k internetu/serveru */
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit{
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideDrinkApi(retrofit: Retrofit): DrinkApi{
        return  retrofit.create(DrinkApi::class.java)
    }
/*
    @Provides
    @Singleton
    fun provideIngredientApi(retrofit: Retrofit): IngredientApi{
        return  retrofit.create(IngredientApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        drinkApi: DrinkApi,
        ingredientApi: IngredientApi,
        drinkDatabase: DrinkDatabase
    ):RemoteDataSource{
        return RemoteDataSourceImpl(
            drinkApi = drinkApi,
            ingredientApi = ingredientApi,
            drinkDatabase = drinkDatabase
        )
    }
*/
    @Provides
    @Singleton
    fun providesRemoteDataSource(
        drinkApi: DrinkApi,
        drinkDatabase: DrinkDatabase
    ):RemoteDataSource{
        return RemoteDataSourceImpl(
            drinkApi = drinkApi,
            drinkDatabase = drinkDatabase
        )
    }

}