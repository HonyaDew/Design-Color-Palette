package com.honey.saved

import com.honey.domain.model.CustomColorScheme
import com.honey.domain.repository.SavedRepository
import com.honey.saved.datasource.internal.SavedDao
import com.honey.saved.extencion.toDataModule
import com.honey.saved.extencion.palettesToDomainModule

class SavedRepositoryImpl(
    private val dao: SavedDao
) : SavedRepository {
    override suspend fun saveColorScheme(palette: CustomColorScheme): Boolean {
        dao.putColorScheme(palette.toDataModule())
        return true
    }

    override suspend fun getAllSavedSchemes(): List<CustomColorScheme> {
        return dao.getAllSavedSchemes().palettesToDomainModule()
    }

    override suspend fun deleteColorScheme(colorScheme: CustomColorScheme): Boolean {
        dao.deleteColorScheme(colorScheme.toDataModule())
        return true
    }
}