package com.honey.saved.extencion

import com.honey.saved.model.ColorInfoData
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomPaletteInfo
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

fun CustomPaletteInfo.toDataModule(): CustomPaletteInfoData{
    return CustomPaletteInfoData(
        colors, name, source
    )
}

fun CustomPaletteInfoData.toDomainModule(): CustomPaletteInfo{
    return CustomPaletteInfo(
        colors, name, source
    )
}

fun List<CustomPaletteInfo>.palettesToDataModule(): List<CustomPaletteInfoData>{
    return map{customPaletteInfo ->
        customPaletteInfo.toDataModule()
    }
}

fun List<CustomPaletteInfoData>.palettesToDomainModule(): List<CustomPaletteInfo>{
    return map { customPaletteInfoData ->
        customPaletteInfoData.toDomainModule()
    }
}