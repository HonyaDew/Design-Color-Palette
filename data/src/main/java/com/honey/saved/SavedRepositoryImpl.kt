package com.honey.saved

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.SavedColorScheme
import com.honey.domain.repository.SavedRepository
import com.honey.saved.datasource.internal.SavedDao
import com.honey.saved.extencion.toDataModule
import com.honey.saved.extencion.colorsToDomainModule
import com.honey.saved.extencion.palettesToDomainModule

class SavedRepositoryImpl(
    private val dao: SavedDao
) : SavedRepository {
    override suspend fun saveColorScheme(palette: SavedColorScheme): Boolean {
        dao.putColorScheme(palette.toDataModule())
        return true
    }

    override suspend fun getAllSavedSchemes(): List<SavedColorScheme> {
        return dao.getAllSavedSchemes().palettesToDomainModule()
    }
}