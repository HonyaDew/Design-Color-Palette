package com.honey.domain.repository

import com.honey.domain.model.SavedColorScheme

interface SavedRepository {
    suspend fun saveColorScheme(palette: SavedColorScheme) : Boolean
    suspend fun getAllSavedSchemes() : List<SavedColorScheme>
}