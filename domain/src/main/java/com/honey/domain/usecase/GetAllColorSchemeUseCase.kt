package com.honey.domain.usecase

import com.honey.domain.model.CustomColorScheme
import com.honey.domain.repository.SavedRepository

class GetAllColorSchemeUseCase(private val savedRepository: SavedRepository) {
    suspend fun invoke() : List<CustomColorScheme> {
        return savedRepository.getAllSavedSchemes()
    }
}