package com.honey.domain.repository

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.Palette

interface PaletteRepository {
    fun getAllColors(): List<ColorInfo>
    fun getColorsByPalette(palette: Palette) : List<ColorInfo>
}