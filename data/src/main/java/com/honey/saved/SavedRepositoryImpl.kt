package com.honey.saved

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomPaletteInfo
import com.honey.domain.repository.SavedRepository
import com.honey.saved.datasource.internal.SavedDao
import com.honey.saved.extencion.toDataModule
import com.honey.saved.extencion.colorsToDomainModule
import com.honey.saved.extencion.palettesToDomainModule

class SavedRepositoryImpl(
    private val dao: SavedDao
) : SavedRepository {
    override suspend fun saveColor(colorInfo: ColorInfo): Boolean {
        dao.putColor(colorInfo.toDataModule())
        return true
    }

    override suspend fun getAllColors(): List<ColorInfo> {
        return dao.getAllSavedColors().colorsToDomainModule()
    }

    override suspend fun savePalette(palette: CustomPaletteInfo): Boolean {
        dao.putCustomPalette(palette.toDataModule())
        return true
    }

    override suspend fun getAllPalettes(): List<CustomPaletteInfo> {
        return dao.getAllSavedPalettes().palettesToDomainModule()
    }
}