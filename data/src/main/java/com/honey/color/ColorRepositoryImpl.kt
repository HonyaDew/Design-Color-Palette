package com.honey.color

import com.honey.color.datasource.extencion.toDataModule
import com.honey.color.datasource.extencion.toDomainModule
import com.honey.color.datasource.internal.ColorDao
import com.honey.color.datasource.internal.ColorDatabase
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.Palette
import com.honey.domain.repository.ColorRepository

class ColorRepositoryImpl(
    private val dao: ColorDao
): ColorRepository {
    override suspend fun getColorsByPalette(palette: Palette): List<ColorInfo> {
        return dao.getColorsByPalette(palette).toDomainModule()
    }

    override suspend fun fillColors(colors: List<ColorInfo>): Boolean {
        dao.fillColors(colors.toDataModule())
        return true
    }
}