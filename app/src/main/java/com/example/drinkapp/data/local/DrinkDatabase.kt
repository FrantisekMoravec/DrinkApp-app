package com.example.drinkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinkapp.data.local.dao.DrinkDao
import com.example.drinkapp.data.local.dao.DrinkRemoteKeyDao
import com.example.drinkapp.domain.model.Drink
import com.example.drinkapp.domain.model.DrinkRemoteKey

@Database(entities = [Drink::class, DrinkRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class DrinkDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
    abstract fun drinkRemoteKeyDao(): DrinkRemoteKeyDao

}