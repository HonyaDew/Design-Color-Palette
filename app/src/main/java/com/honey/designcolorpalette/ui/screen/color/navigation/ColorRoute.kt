package com.honey.designcolorpalette.ui.screen.color.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honey.designcolorpalette.ui.screen.color.ColorScreen
import com.honey.designcolorpalette.ui.screen.color.ColorViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ColorRoute() {
    val viewModel = getViewModel<ColorViewModel>()

    ColorScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event) }
    )
}