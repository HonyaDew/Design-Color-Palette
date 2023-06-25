package com.honey.domain.repository

import com.honey.domain.model.CustomColorScheme

interface SavedRepository {
    suspend fun saveColorScheme(palette: CustomColorScheme) : Boolean
    suspend fun getAllSavedSchemes() : List<CustomColorScheme>
    suspend fun deleteColorScheme(colorScheme: CustomColorScheme) : Boolean
}