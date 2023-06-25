package com.honey.domain.usecase

import com.honey.domain.model.CustomColorScheme
import com.honey.domain.repository.SavedRepository

class SaveColorSchemeUseCase (private val savedRepository: SavedRepository) {
    suspend fun invoke(colorScheme: CustomColorScheme) : Boolean{
        return savedRepository.saveColorScheme(colorScheme)
    }
}