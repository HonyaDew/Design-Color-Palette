package com.honey.designcolorpalette.ui.screen.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honey.domain.model.ColorInfo
import com.honey.designcolorpalette.ui.screen.palette.PaletteScreen
import com.honey.designcolorpalette.ui.screen.palette.PaletteViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PaletteRoute(
    onColorClick : (color: ColorInfo) -> Unit
) {
    val viewModel = getViewModel<PaletteViewModel>()

    PaletteScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event) },
        onColorClick = onColorClick
    )
}