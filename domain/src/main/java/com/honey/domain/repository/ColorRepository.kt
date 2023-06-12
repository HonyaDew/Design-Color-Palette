package com.honey.domain.repository

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.Palette

interface ColorRepository {
    suspend fun getColorsByPalette(palette: Palette) : List<ColorInfo>
}