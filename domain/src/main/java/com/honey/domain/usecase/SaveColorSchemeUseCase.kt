package com.honey.domain.usecase

import com.honey.domain.model.SavedColorScheme
import com.honey.domain.repository.SavedRepository

class SaveColorSchemeUseCase (private val savedRepository: SavedRepository) {
    suspend fun invoke(colorScheme: SavedColorScheme) : Boolean{
        return savedRepository.saveColorScheme(colorScheme)
    }
}