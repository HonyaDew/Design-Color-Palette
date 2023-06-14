package com.honey.designcolorpalette.ui.screen.sliders.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honey.domain.model.ColorInfo
import com.honey.designcolorpalette.ui.screen.sliders.SlidersScreen
import com.honey.designcolorpalette.ui.screen.sliders.SlidersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SlidersRoute(
    onColorClick : (color: ColorInfo) -> Unit,
    viewModel: SlidersViewModel = getViewModel<SlidersViewModel>()
) {

    SlidersScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event) },
        onColorClick = onColorClick
    )
}