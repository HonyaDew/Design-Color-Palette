package com.honey.domain.usecase

import com.honey.domain.model.CustomColorScheme
import com.honey.domain.repository.SavedRepository

class DeleteColorSchemeUseCase(private val savedRepository: SavedRepository) {
    suspend fun invoke(colorScheme: CustomColorScheme){
        savedRepository.deleteColorScheme(colorScheme)
    }
}