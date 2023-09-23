package com.honyadew.domain.usecase

import com.honyadew.model.CustomColorScheme
import com.honyadew.domain.repository.SavedRepository

class DeleteColorSchemeUseCase(private val savedRepository: SavedRepository) {
    suspend fun invoke(colorScheme: CustomColorScheme){
        savedRepository.deleteColorScheme(colorScheme)
    }
}