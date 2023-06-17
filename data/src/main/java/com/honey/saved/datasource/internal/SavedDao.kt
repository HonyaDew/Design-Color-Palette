package com.honey.saved.datasource.internal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.honey.domain.model.CustomPaletteInfo
import com.honey.domain.model.CustomPaletteSources
import com.honey.domain.model.Palette
import com.honey.saved.model.ColorInfoConverter
import com.honey.saved.model.ColorInfoData
import com.honey.saved.model.CustomPaletteInfoData
import com.honey.saved.model.PaletteTypeConverter
import com.honey.saved.model.SourceConverter

@Dao
@TypeConverters(ColorInfoConverter::class, SourceConverter::class, PaletteTypeConverter::class)
interface SavedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putColor(color: ColorInfoData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putCustomPalette(customPalette: CustomPaletteInfoData)

    @Query("SELECT * FROM color_table")
    suspend fun getAllSavedColors() : List<ColorInfoData>

    @Query("SELECT * FROM palette_table")
    suspend fun getAllSavedPalettes() : List<CustomPaletteInfoData>

}