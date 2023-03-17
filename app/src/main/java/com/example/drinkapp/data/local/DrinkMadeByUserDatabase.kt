package com.example.drinkapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.drinkapp.data.local.dao.DrinkMadeByUserDao
import com.example.drinkapp.domain.model.DrinkMadeByUser

@Database(entities = [DrinkMadeByUser::class], version = 1, exportSchema = false)
abstract class DrinkMadeByUserDatabase: RoomDatabase(){

    abstract fun drinkMadeByUserDao(): DrinkMadeByUserDao

}