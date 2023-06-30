package com.honey.designcolorpalette.ui.screen.image.navigation

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honey.designcolorpalette.ui.screen.image.ImageScreen
import com.honey.designcolorpalette.ui.screen.image.ImageViewModel
import com.honey.domain.model.ColorInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun ImageRoute(
    onColorClick : (color: ColorInfo) -> Unit,
    viewModel: ImageViewModel = getViewModel()
) {
    ImageScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event)},
        onColorClick = onColorClick
    )
}