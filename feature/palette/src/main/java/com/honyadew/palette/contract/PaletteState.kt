package com.honyadew.palette.contract

import com.honyadew.base.ViewState

sealed class PaletteState : com.honyadew.base.ViewState {
    object Loading : PaletteState()
    data class Show(
        val colorsToShow: List<com.honyadew.model.ColorInfo>,
        val palette: com.honyadew.model.Palette,
    ) : PaletteState()
}