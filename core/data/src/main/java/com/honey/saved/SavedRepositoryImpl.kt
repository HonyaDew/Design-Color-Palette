package com.honey.saved

import com.honyadew.model.CustomColorScheme
import com.honey.domain.repository.SavedRepository
import com.honey.saved.datasource.internal.SavedDao
import com.honey.saved.extencion.toDataModule
import com.honey.saved.extencion.palettesToDomainModule

class SavedRepositoryImpl(
    private val dao: SavedDao
) : SavedRepository {
    override suspend fun saveColorScheme(palette: com.honyadew.model.CustomColorScheme): Boolean {
        dao.putColorScheme(palette.toDataModule())
        return true
    }

    override suspend fun getAllSavedSchemes(): List<com.honyadew.model.CustomColorScheme> {
        return dao.getAllSavedSchemes().palettesToDomainModule()
    }

    override suspend fun deleteColorScheme(colorScheme: com.honyadew.model.CustomColorScheme): Boolean {
        dao.deleteColorScheme(colorScheme.toDataModule())
        return true
    }
}