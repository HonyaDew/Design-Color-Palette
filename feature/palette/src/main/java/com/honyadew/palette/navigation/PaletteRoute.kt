package com.honyadew.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honyadew.model.ColorInfo
import com.honyadew.palette.PaletteScreen
import com.honyadew.palette.PaletteViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PaletteRoute(
    onColorClick : (color: com.honyadew.model.ColorInfo) -> Unit,
    viewModel : PaletteViewModel = getViewModel<PaletteViewModel>()
) {

    PaletteScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event) },
        onColorClick = onColorClick
    )
}