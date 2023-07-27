package com.honey.saved.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.honey.saved.datasource.internal.Constance
import com.honyadew.model.Palette
import kotlinx.serialization.json.Json

@Entity(tableName = Constance.COLOR_TABLE_NAME)
@TypeConverters(PaletteTypeConverter::class)
data class ColorInfoData(
    @PrimaryKey(autoGenerate = false)
    val value: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "palette")
    val palette: com.honyadew.model.Palette
)



class PaletteTypeConverter {
    @TypeConverter
    fun fromDatabaseValue(value: String): com.honyadew.model.Palette {
        return Json.decodeFromString(com.honyadew.model.Palette.serializer(),value)
    }
    @TypeConverter
    fun toDatabaseValue(palette: com.honyadew.model.Palette): String {
        return Json.encodeToString(com.honyadew.model.Palette.serializer(), palette)
    }
}
