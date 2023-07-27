package com.honyadew.saved.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.honyadew.saved.SavedScreen
import com.honyadew.saved.SavedViewModel
import com.honyadew.model.ColorInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun SavedRoute(
    onColorClick: (color: ColorInfo) -> Unit,
    viewModel : SavedViewModel = getViewModel<SavedViewModel>()
) {
    SavedScreen(
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event)},
        onColorClick = onColorClick
    )

    //Notify a viewModel for User navigated here
    LaunchedEffect(Unit){
        viewModel.loadColorSchemes()
    }
}