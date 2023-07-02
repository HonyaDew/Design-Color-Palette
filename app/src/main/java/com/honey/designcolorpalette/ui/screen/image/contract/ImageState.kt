package com.honey.designcolorpalette.ui.screen.image.contract

import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.base.ViewState
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomColorScheme

sealed class ImageState : ViewState {
    object Loading : ImageState()
    data class Show(
        val selectedColor: Color,
        val colorsToSave : List<ColorInfo> = emptyList(),
        val extractedColors : List<ColorInfo> = emptyList()
    ) : ImageState()
}