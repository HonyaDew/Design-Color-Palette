package com.honey.designcolorpalette.ui.screen.sliders.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.honey.designcolorpalette.ui.screen.sliders.SlidersScreen
import com.honey.designcolorpalette.ui.screen.sliders.SlidersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SlidersRoute(
    onColorClick : (color: String) -> Unit
) {
    val viewModel = getViewModel<SlidersViewModel>()

    SlidersScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event) },
        onColorClick = onColorClick
    )
}