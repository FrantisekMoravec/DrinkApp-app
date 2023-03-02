package com.example.drinkapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.drinkapp.util.Constants.DRINK_REMOTE_KEYS_DATABASE_TABLE

/** entita klíčů drinku */
/** díky klíčům pozná knihovna paging3 jakou stránku s obsahem zobrazovat */

@Entity(tableName = DRINK_REMOTE_KEYS_DATABASE_TABLE)
data class DrinkRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
