package com.honey.color.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.honey.color.datasource.internal.Constance
import com.honey.domain.model.Palette
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = Constance.TABLE_NAME)
@TypeConverters(PaletteTypeConverter::class)
data class ColorInfoData(
    @ColumnInfo(name = "value")
    val value: String,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    @ColumnInfo(name = "palette")
    val palette: Palette
)



class PaletteTypeConverter {
    @TypeConverter
    fun fromDatabaseValue(value: String): Palette {
        return Json.decodeFromString(value)
    }
    @TypeConverter
    fun toDatabaseValue(palette: Palette): String {
        return Json.encodeToString(palette)
    }
}
