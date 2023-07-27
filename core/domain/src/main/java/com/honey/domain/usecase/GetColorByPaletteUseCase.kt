package com.honey.domain.usecase

import com.honyadew.model.ColorInfo
import com.honyadew.model.Palette
import com.honey.domain.repository.PaletteRepository

class GetColorByPaletteUseCase(private val repository: PaletteRepository) {
    fun invoke(palette: Palette) : List<ColorInfo>{
        return repository.getColorsByPalette(palette)
    }
}