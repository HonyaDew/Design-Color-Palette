package com.honey.domain.usecase

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.Palette
import com.honey.domain.repository.ColorRepository

class GetColorByPaletteUseCase(private val repository: ColorRepository) {
    suspend fun invoke(palette: Palette) : List<ColorInfo>{
        return repository.getColorsByPalette(palette)
    }
}