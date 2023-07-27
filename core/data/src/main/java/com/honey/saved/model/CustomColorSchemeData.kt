package com.honey.saved.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.honey.saved.datasource.internal.Constance
import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorSchemeSource
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Entity(tableName = Constance.PALETTE_TABLE_NAME)
@TypeConverters(ColorInfoConverter::class, SourceConverter::class)
data class CustomColorSchemeData(
    @PrimaryKey(autoGenerate = false)
    val colors : List<com.honyadew.model.ColorInfo>,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "source")
    val source: com.honyadew.model.ColorSchemeSource
)

class ColorInfoConverter{
    @TypeConverter
    fun fromDatabaseValue(value: String) : List<com.honyadew.model.ColorInfo> {
        val serializer = com.honyadew.model.ColorInfo.serializer()
        val serializerList = ListSerializer(serializer)
        return Json.decodeFromString(serializerList, value)
    }

    @TypeConverter
    fun toDatabaseValue(colorInfoList: List<com.honyadew.model.ColorInfo>) : String {
        val serializer = com.honyadew.model.ColorInfo.serializer()
        val serializerList = ListSerializer(serializer)
        return Json.encodeToString(serializerList, colorInfoList)
    }
}

class SourceConverter {
    @TypeConverter
    fun fromDatabaseValue(value: String) : com.honyadew.model.ColorSchemeSource =
        Json.decodeFromString(com.honyadew.model.ColorSchemeSource.serializer(), value)

    @TypeConverter
    fun toDatabaseValue(source: com.honyadew.model.ColorSchemeSource) : String =
        Json.encodeToString(com.honyadew.model.ColorSchemeSource.serializer(), source)
}