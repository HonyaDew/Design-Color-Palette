package com.honyadew.sliders.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honyadew.model.ColorInfo
import com.honyadew.sliders.SlidersScreen
import com.honyadew.sliders.SlidersViewModel
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