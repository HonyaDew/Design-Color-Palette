package com.honey.saved.extencion

import com.honey.saved.model.ColorInfoData
import com.honyadew.model.ColorInfo
import com.honyadew.model.CustomColorScheme
import com.honey.saved.model.CustomColorSchemeData

fun com.honyadew.model.ColorInfo.toDataModule(): ColorInfoData {
    return ColorInfoData(
        value, name, palette
    )
}

fun ColorInfoData.toDomainModule(): com.honyadew.model.ColorInfo {
    return com.honyadew.model.ColorInfo(
        value, name, palette
    )
}

fun List<com.honyadew.model.ColorInfo>.colorsToDataModule(): List<ColorInfoData> {
    return map {colorInfo ->
        colorInfo.toDataModule()
    }
}

fun List<ColorInfoData>.colorsToDomainModule():List<com.honyadew.model.ColorInfo> {
    return map { colorInfoData ->
        colorInfoData.toDomainModule()
    }
}

fun com.honyadew.model.CustomColorScheme.toDataModule(): CustomColorSchemeData{
    return CustomColorSchemeData(
        colors, name, source
    )
}

fun CustomColorSchemeData.toDomainModule(): com.honyadew.model.CustomColorScheme {
    return com.honyadew.model.CustomColorScheme(
        colors, name, source
    )
}

fun List<com.honyadew.model.CustomColorScheme>.palettesToDataModule(): List<CustomColorSchemeData>{
    return map{customPaletteInfo ->
        customPaletteInfo.toDataModule()
    }
}

fun List<CustomColorSchemeData>.palettesToDomainModule(): List<com.honyadew.model.CustomColorScheme>{
    return map { customPaletteInfoData ->
        customPaletteInfoData.toDomainModule()
    }
}