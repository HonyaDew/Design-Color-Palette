package com.honey.domain.repository

import com.honyadew.model.CustomColorScheme

interface SavedRepository {
    suspend fun saveColorScheme(palette: CustomColorScheme) : Boolean
    suspend fun getAllSavedSchemes() : List<CustomColorScheme>
    suspend fun deleteColorScheme(colorScheme: CustomColorScheme) : Boolean
}