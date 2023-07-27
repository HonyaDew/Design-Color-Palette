package com.honey.domain.repository

import com.honyadew.model.ColorInfo
import com.honyadew.model.Palette

interface PaletteRepository {
    fun getAllColors(): List<ColorInfo>
    fun getColorsByPalette(palette: Palette) : List<ColorInfo>
}