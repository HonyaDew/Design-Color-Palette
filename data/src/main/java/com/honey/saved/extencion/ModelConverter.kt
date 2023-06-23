package com.honey.saved.extencion

import com.honey.saved.model.ColorInfoData
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.SavedColorScheme
import com.honey.saved.model.CustomPaletteInfoData

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

fun SavedColorScheme.toDataModule(): CustomPaletteInfoData{
    return CustomPaletteInfoData(
        colors, name, source
    )
}

fun CustomPaletteInfoData.toDomainModule(): SavedColorScheme{
    return SavedColorScheme(
        colors, name, source
    )
}

fun List<SavedColorScheme>.palettesToDataModule(): List<CustomPaletteInfoData>{
    return map{customPaletteInfo ->
        customPaletteInfo.toDataModule()
    }
}

fun List<CustomPaletteInfoData>.palettesToDomainModule(): List<SavedColorScheme>{
    return map { customPaletteInfoData ->
        customPaletteInfoData.toDomainModule()
    }
}