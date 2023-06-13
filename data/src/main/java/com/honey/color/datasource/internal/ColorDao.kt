package com.honey.color.datasource.internal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.honey.color.datasource.model.ColorInfoData
import com.honey.color.datasource.model.PaletteTypeConverter
import com.honey.domain.model.Palette

@Dao
@TypeConverters(PaletteTypeConverter::class)
interface ColorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun fillColors(colors: List<ColorInfoData>)

    @Query("SELECT * FROM color_table WHERE palette = :palette")
    suspend fun getColorsByPalette(palette: Palette) : List<ColorInfoData>
}