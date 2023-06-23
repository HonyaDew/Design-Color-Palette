package com.honey.domain.usecase

import com.honey.domain.model.SavedColorScheme
import com.honey.domain.repository.SavedRepository

class GetAllColorSchemeUseCase(private val savedRepository: SavedRepository) {
    suspend fun invoke() : List<SavedColorScheme> {
        return savedRepository.getAllSavedSchemes()
    }
}