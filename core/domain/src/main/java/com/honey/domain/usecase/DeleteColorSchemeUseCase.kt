package com.honey.domain.usecase

import com.honyadew.model.CustomColorScheme
import com.honey.domain.repository.SavedRepository

class DeleteColorSchemeUseCase(private val savedRepository: SavedRepository) {
    suspend fun invoke(colorScheme: CustomColorScheme){
        savedRepository.deleteColorScheme(colorScheme)
    }
}