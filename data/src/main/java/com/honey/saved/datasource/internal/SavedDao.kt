package com.honey.saved.datasource.internal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.honey.saved.model.ColorInfoConverter
import com.honey.saved.model.CustomColorSchemeData
import com.honey.saved.model.PaletteTypeConverter
import com.honey.saved.model.SourceConverter

@Dao
@TypeConverters(ColorInfoConverter::class, SourceConverter::class, PaletteTypeConverter::class)
interface SavedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putColorScheme(customPalette: CustomColorSchemeData)

    @Query("SELECT * FROM palette_table")
    suspend fun getAllSavedSchemes() : List<CustomColorSchemeData>

    @Delete
    suspend fun deleteColorScheme(colorScheme: CustomColorSchemeData)

}