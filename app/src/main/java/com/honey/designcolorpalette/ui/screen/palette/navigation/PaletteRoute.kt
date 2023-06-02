package com.honey.designcolorpalette.ui.screen.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.ui.screen.palette.PaletteScreen
import com.honey.designcolorpalette.ui.screen.palette.PaletteViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PaletteRoute(
    onColorClick : (color: Color) -> Unit
) {
    val viewModel = getViewModel<PaletteViewModel>()

    PaletteScreen()
}