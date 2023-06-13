package com.honey.domain.usecase

import com.honey.domain.model.ColorInfo
import com.honey.domain.repository.ColorRepository

//Only using for create file for prepopulate
class FillColorsUseCase (private val repository: ColorRepository) {
    suspend fun invoke(colors: List<ColorInfo>): Boolean{
        return repository.fillColors(colors)
    }
}