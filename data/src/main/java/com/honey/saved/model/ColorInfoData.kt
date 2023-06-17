package com.honey.saved.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.honey.saved.datasource.internal.Constance
import com.honey.domain.model.Palette
import kotlinx.serialization.json.Json

@Entity(tableName = Constance.COLOR_TABLE_NAME)
@TypeConverters(PaletteTypeConverter::class)
data class ColorInfoData(
    @PrimaryKey(autoGenerate = false)
    val value: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "palette")
    val palette: Palette
)



class PaletteTypeConverter {
    @TypeConverter
    fun fromDatabaseValue(value: String): Palette {
        return Json.decodeFromString(Palette.serializer(),value)
    }
    @TypeConverter
    fun toDatabaseValue(palette: Palette): String {
        return Json.encodeToString(Palette.serializer(), palette)
    }
}
