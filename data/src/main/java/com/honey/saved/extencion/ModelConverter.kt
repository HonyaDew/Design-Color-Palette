package com.honey.saved.extencion

import com.honey.saved.model.ColorInfoData
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomColorScheme
import com.honey.saved.model.CustomColorSchemeData

fun ColorInfo.toDataModule(): ColorInfoData {
    return ColorInfoData(
        value, name, palette
    )
}

fun ColorInfoData.toDomainModule(): ColorInfo {
    return ColorInfo(
        value, name, palette
    )
}

fun List<ColorInfo>.colorsToDataModule(): List<ColorInfoData> {
    return map {colorInfo ->
        colorInfo.toDataModule()
    }
}

fun List<ColorInfoData>.colorsToDomainModule():List<ColorInfo> {
    return map { colorInfoData ->
        colorInfoData.toDomainModule()
    }
}

fun CustomColorScheme.toDataModule(): CustomColorSchemeData{
    return CustomColorSchemeData(
        colors, name, source
    )
}

fun CustomColorSchemeData.toDomainModule(): CustomColorScheme{
    return CustomColorScheme(
        colors, name, source
    )
}

fun List<CustomColorScheme>.palettesToDataModule(): List<CustomColorSchemeData>{
    return map{customPaletteInfo ->
        customPaletteInfo.toDataModule()
    }
}

fun List<CustomColorSchemeData>.palettesToDomainModule(): List<CustomColorScheme>{
    return map { customPaletteInfoData ->
        customPaletteInfoData.toDomainModule()
    }
}