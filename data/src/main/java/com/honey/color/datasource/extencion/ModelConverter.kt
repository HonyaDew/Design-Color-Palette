package com.honey.color.datasource.extencion

import com.honey.color.datasource.model.ColorInfoData
import com.honey.domain.model.ColorInfo

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

fun List<ColorInfo>.toDataModule(): List<ColorInfoData> {
    return map {colorInfo ->
        colorInfo.toDataModule()
    }
}

fun List<ColorInfoData>.toDomainModule():List<ColorInfo> {
    return map { colorInfoData ->
        colorInfoData.toDomainModule()
    }
}