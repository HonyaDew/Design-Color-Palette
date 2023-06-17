package com.honey.domain.repository

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomPaletteInfo

interface SavedRepository {
    suspend fun saveColor(colorInfo: ColorInfo) : Boolean
    suspend fun getAllColors() : List<ColorInfo>
    suspend fun savePalette(palette: CustomPaletteInfo) : Boolean
    suspend fun getAllPalettes() : List<CustomPaletteInfo>
}