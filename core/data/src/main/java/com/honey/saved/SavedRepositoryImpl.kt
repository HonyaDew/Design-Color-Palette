package com.honey.saved

import com.honyadew.model.CustomColorScheme
import com.honey.domain.repository.SavedRepository
import com.honyadew.database.dao.SavedDao
import com.honyadew.database.model.listToExternal
import com.honyadew.database.model.toEntity

class SavedRepositoryImpl(
    private val dao: SavedDao
) : SavedRepository {
    override suspend fun saveColorScheme(palette: CustomColorScheme): Boolean {
        dao.putColorScheme(palette.toEntity())
        return true
    }

    override suspend fun getAllSavedSchemes(): List<CustomColorScheme> {
        return dao.getAllSavedSchemes().listToExternal()
    }

    override suspend fun deleteColorScheme(colorScheme: CustomColorScheme): Boolean {
        dao.deleteColorScheme(colorScheme.toEntity())
        return true
    }
}