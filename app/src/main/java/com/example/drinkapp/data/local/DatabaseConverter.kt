package com.example.drinkapp.data.local

import android.util.Log
import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ";"

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list){
            Log.d("ingredient", "db converter(item): $item")
            stringBuilder.append(item).append(separator)
        }

        Log.d("ingredient", "db converter(stringBuilder): $stringBuilder")
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }
    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }
}