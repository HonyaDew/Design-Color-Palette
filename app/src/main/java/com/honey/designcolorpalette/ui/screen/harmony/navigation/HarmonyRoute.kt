package com.honey.designcolorpalette.ui.screen.harmony.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honey.designcolorpalette.ui.screen.harmony.HarmonyScreen
import com.honey.designcolorpalette.ui.screen.harmony.HarmonyViewModel
import com.honey.domain.model.ColorInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun HarmonyRoute(
    onColorClick : (color: ColorInfo) -> Unit,
    viewModel: HarmonyViewModel = getViewModel()
) {

    HarmonyScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event)},
        onColorClick = onColorClick
    )
}