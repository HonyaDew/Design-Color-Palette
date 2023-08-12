package com.honyadew.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.honyadew.database.Constance
import com.honyadew.model.ColorInfo

@Entity(tableName = Constance.COLOR_TABLE_NAME)
//@TypeConverters(PaletteTypeConverter::class)
data class ColorInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val value: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "palette")
    val palette: com.honyadew.model.Palette
)

fun ColorInfo.toEntity(): ColorInfoEntity {
    return ColorInfoEntity(
        value, name, palette
    )
}

fun ColorInfoEntity.toExternal(): ColorInfo {
    return ColorInfo(
        value, name, palette
    )
}

fun List<ColorInfo>.listToEntity(): List<ColorInfoEntity> {
    return map {colorInfo ->
        colorInfo.toEntity()
    }
}

fun List<ColorInfoEntity>.listToExternal():List<ColorInfo> {
    return map { colorInfoData ->
        colorInfoData.toExternal()
    }
}