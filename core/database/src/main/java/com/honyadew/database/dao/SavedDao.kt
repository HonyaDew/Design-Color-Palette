package com.honyadew.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.honyadew.database.model.CustomColorSchemeEntity
import com.honyadew.database.unil.ColorInfoConverter
import com.honyadew.database.unil.PaletteTypeConverter
import com.honyadew.database.unil.SourceConverter

@Dao
interface SavedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putColorScheme(customPalette: CustomColorSchemeEntity)

    @Query("SELECT * FROM palette_table")
    suspend fun getAllSavedSchemes() : List<CustomColorSchemeEntity>

    @Delete
    suspend fun deleteColorScheme(colorScheme: CustomColorSchemeEntity)

}