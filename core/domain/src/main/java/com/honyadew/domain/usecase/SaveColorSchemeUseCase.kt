package com.honyadew.domain.usecase

import com.honyadew.model.CustomColorScheme
import com.honyadew.domain.repository.SavedRepository

class SaveColorSchemeUseCase (private val savedRepository: SavedRepository) {
    suspend fun invoke(colorScheme: CustomColorScheme) : Boolean{
        return savedRepository.saveColorScheme(colorScheme)
    }
}