package com.example.drinkapp.di

import com.example.drinkapp.data.repository.Repository
import com.example.drinkapp.domain.use_cases.UseCases
import com.example.drinkapp.domain.use_cases.get_all_ingredient_families.GetAllIngredientFamiliesUseCase
import com.example.drinkapp.domain.use_cases.get_all_remote_drinks.GetAllRemoteDrinksUseCase
import com.example.drinkapp.domain.use_cases.get_all_ingredients.GetAllIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_all_local_drinks.GetAllLocalDrinksUseCase
import com.example.drinkapp.domain.use_cases.get_drinks_containing_ingredients.GetDrinksContainingIngredientsUseCase
import com.example.drinkapp.domain.use_cases.get_ingredient_families_by_name.GetSelectedIngredientFamiliesByName
import com.example.drinkapp.domain.use_cases.get_ingredients_by_names.GetSelectedIngredientsByNamesUseCase
import com.example.drinkapp.domain.use_cases.get_selected_drink.GetSelectedLocalDrinkUseCase
import com.example.drinkapp.domain.use_cases.get_selected_ingredient_family.GetSelectedIngredientFamilyByIdUseCase
import com.example.drinkapp.domain.use_cases.search_drinks.SearchDrinksUseCase
import com.example.drinkapp.domain.use_cases.search_ingredient_families.SearchIngredientFamiliesUseCase
import com.example.drinkapp.domain.use_cases.search_ingredients.SearchIngredientsUseCase
import com.example.drinkapp.domain.use_cases.search_ingredients_by_ingredient_family_names.SearchIngredientsByIngredientFamilyNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            getAllRemoteDrinksUseCase = GetAllRemoteDrinksUseCase(repository),
            getAllLocalDrinksUseCase = GetAllLocalDrinksUseCase(repository),
            searchDrinksUseCase = SearchDrinksUseCase(repository),

            getAllIngredientsUseCase = GetAllIngredientsUseCase(repository),
            searchIngredientsUseCase = SearchIngredientsUseCase(repository),

            getSelectedLocalDrinkUseCase = GetSelectedLocalDrinkUseCase(repository),

            //getAllIngredientsMadeByUserUseCase = GetAllIngredientsMadeByUserUseCase(repository),

            getDrinksContainingIngredientsUseCase = GetDrinksContainingIngredientsUseCase(repository),
            searchIngredientsByIngredientFamilyNameUseCase = SearchIngredientsByIngredientFamilyNameUseCase(repository),

            getSelectedIngredientsByNamesUseCase = GetSelectedIngredientsByNamesUseCase(repository),

            getAllIngredientFamiliesUseCase = GetAllIngredientFamiliesUseCase(repository),
            getSelectedIngredientFamiliesByName = GetSelectedIngredientFamiliesByName(repository),
            searchIngredientFamiliesUseCase = SearchIngredientFamiliesUseCase(repository),
            getSelectedIngredientFamilyByIdUseCase = GetSelectedIngredientFamilyByIdUseCase(repository)
        )
    }
}