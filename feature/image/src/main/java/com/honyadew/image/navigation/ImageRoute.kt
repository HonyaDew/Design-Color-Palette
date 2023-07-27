package com.honyadew.image.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honyadew.image.ImageScreen
import com.honyadew.image.ImageViewModel
import com.honyadew.model.ColorInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun ImageRoute(
    onColorClick : (color: com.honyadew.model.ColorInfo) -> Unit,
    viewModel: ImageViewModel = getViewModel()
) {
    ImageScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event)},
        onColorClick = onColorClick
    )
}