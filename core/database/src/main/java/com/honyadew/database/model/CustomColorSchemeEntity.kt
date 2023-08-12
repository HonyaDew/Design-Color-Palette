package com.honyadew.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.honyadew.database.Constance
import com.honyadew.model.CustomColorScheme

@Entity(tableName = Constance.PALETTE_TABLE_NAME)
//@TypeConverters(ColorInfoConverter::class, SourceConverter::class)
data class CustomColorSchemeEntity(
    @PrimaryKey(autoGenerate = false)
    val colors : List<com.honyadew.model.ColorInfo>,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "source")
    val source: com.honyadew.model.ColorSchemeSource
)

fun CustomColorScheme.toEntity(): CustomColorSchemeEntity {
    return CustomColorSchemeEntity(
        colors, name, source
    )
}

fun CustomColorSchemeEntity.toExternal(): CustomColorScheme {
    return CustomColorScheme(
        colors, name, source
    )
}

fun List<CustomColorScheme>.listToEntity(): List<CustomColorSchemeEntity>{
    return map{customPaletteInfo ->
        customPaletteInfo.toEntity()
    }
}

fun List<CustomColorSchemeEntity>.listToExternal(): List<CustomColorScheme>{
    return map { customPaletteInfoData ->
        customPaletteInfoData.toExternal()
    }
}