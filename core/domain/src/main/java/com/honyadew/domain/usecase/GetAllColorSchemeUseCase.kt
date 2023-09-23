package com.honyadew.domain.usecase

import com.honyadew.model.CustomColorScheme
import com.honyadew.domain.repository.SavedRepository

class GetAllColorSchemeUseCase(private val savedRepository: SavedRepository) {
    suspend fun invoke() : List<CustomColorScheme> {
        return savedRepository.getAllSavedSchemes()
    }
}