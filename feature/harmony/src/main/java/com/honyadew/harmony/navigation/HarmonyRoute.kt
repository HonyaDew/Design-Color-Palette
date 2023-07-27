package com.honyadew.harmony.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.honyadew.harmony.HarmonyScreen
import com.honyadew.harmony.HarmonyViewModel
import com.honyadew.model.ColorInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun HarmonyRoute(
    onColorClick : (color: com.honyadew.model.ColorInfo) -> Unit,
    viewModel: HarmonyViewModel = getViewModel()
) {

    HarmonyScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event)},
        onColorClick = onColorClick
    )
}