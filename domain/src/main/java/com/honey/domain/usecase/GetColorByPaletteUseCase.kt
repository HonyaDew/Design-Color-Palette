package com.honey.domain.usecase

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.Palette
import com.honey.domain.repository.PaletteRepository

class GetColorByPaletteUseCase(private val repository: PaletteRepository) {
    fun invoke(palette: Palette) : List<ColorInfo>{
        return repository.getColorsByPalette(palette)
    }
}