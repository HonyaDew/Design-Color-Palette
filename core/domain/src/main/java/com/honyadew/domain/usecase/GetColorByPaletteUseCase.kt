package com.honyadew.domain.usecase

import com.honyadew.model.ColorInfo
import com.honyadew.model.Palette
import com.honyadew.domain.repository.PaletteRepository

class GetColorByPaletteUseCase(private val paletteRepository: PaletteRepository) {
    fun invoke(palette: Palette) : List<ColorInfo>{
        return paletteRepository.getColorsByPalette(palette)
    }
}