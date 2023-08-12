package com.honyadew.database.unil

import androidx.room.TypeConverter
import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorSchemeSource
import com.honyadew.model.Palette
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

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
    fun fromDatabaseValue(value: String) : ColorSchemeSource =
        Json.decodeFromString(ColorSchemeSource.serializer(), value)

    @TypeConverter
    fun toDatabaseValue(source: com.honyadew.model.ColorSchemeSource) : String =
        Json.encodeToString(ColorSchemeSource.serializer(), source)
}