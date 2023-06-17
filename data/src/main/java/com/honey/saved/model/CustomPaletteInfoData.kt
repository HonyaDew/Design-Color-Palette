package com.honey.saved.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.honey.saved.datasource.internal.Constance
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomPaletteSources
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Entity(tableName = Constance.PALETTE_TABLE_NAME)
@TypeConverters(ColorInfoConverter::class, SourceConverter::class)
data class CustomPaletteInfoData(
    @PrimaryKey(autoGenerate = false)
    val colors : List<ColorInfo>,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "source")
    val source: CustomPaletteSources
)

class ColorInfoConverter{
    @TypeConverter
    fun fromDatabaseValue(value: String) : List<ColorInfo> {
        val serializer = ColorInfo.serializer()
        val serializerList = ListSerializer(serializer)
        return Json.decodeFromString(serializerList, value)
    }

    @TypeConverter
    fun toDatabaseValue(colorInfoList: List<ColorInfo>) : String {
        val serializer = ColorInfo.serializer()
        val serializerList = ListSerializer(serializer)
        return Json.encodeToString(serializerList, colorInfoList)
    }
}

class SourceConverter {
    @TypeConverter
    fun fromDatabaseValue(value: String) : CustomPaletteSources =
        Json.decodeFromString(CustomPaletteSources.serializer(), value)

    @TypeConverter
    fun toDatabaseValue(source: CustomPaletteSources) : String =
        Json.encodeToString(CustomPaletteSources.serializer(), source)
}