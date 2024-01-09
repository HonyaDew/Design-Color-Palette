package com.honyadew.domain.usecase

import com.honyadew.domain.repository.SavedRepository
import com.honyadew.model.CustomColorScheme

class ChangeSchemeTitleUseCase(private val savedRepository: SavedRepository) {
    suspend fun invoke(newTitle: String, scheme: CustomColorScheme){
        savedRepository.deleteColorScheme(scheme)
        savedRepository.saveColorScheme(scheme.copy(name = newTitle))
    }
}