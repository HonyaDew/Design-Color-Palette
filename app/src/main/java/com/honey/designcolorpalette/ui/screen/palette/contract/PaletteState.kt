package com.honey.designcolorpalette.ui.screen.palette.contract

import com.honey.designcolorpalette.base.ViewState
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.Palette

sealed class PaletteState : ViewState {
    object Loading : PaletteState()
    data class Show(
        val colorsToShow: List<ColorInfo>,
        val palette: Palette,
    ) : PaletteState()
}