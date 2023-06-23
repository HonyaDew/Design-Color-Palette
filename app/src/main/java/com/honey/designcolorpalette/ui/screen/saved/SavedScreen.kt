package com.honey.designcolorpalette.ui.screen.saved

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEffect
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEvent
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedState
import com.honey.designcolorpalette.ui.screen.saved.model.SavedTabs
import com.honey.designcolorpalette.ui.screen.saved.view.SavedViewLoading
import com.honey.designcolorpalette.ui.screen.saved.view.SavedViewShow
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.SavedColorScheme
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun SavedScreen (
    state : State<SavedState>,
    effect: SharedFlow<SavedEffect?>,
    onEventSend: (event: SavedEvent) -> Unit,
    onColorClick: (color: ColorInfo) -> Unit
){
    val coroutine = rememberCoroutineScope()

    when (val state = state.value){
        is SavedState.Loading -> {
            SavedViewLoading(state = state)
        }
        is SavedState.Show -> {
            SavedViewShow(
                state = state,
                onDeleteClick = {colorScheme ->
                    onEventSend.invoke(SavedEvent.DeleteColorScheme(colorScheme))
                },
                onOpenColorScheme = {colorScheme -> 
                    onEventSend.invoke(SavedEvent.OpenColorScheme(colorScheme))
                },
                onDismissOpen = {
                    onEventSend.invoke(SavedEvent.CloseColorScheme)
                },
                onChangeTab = {newTab -> 
                    onEventSend.invoke(SavedEvent.ChangeFilterTab(newTab))
                }
            )
        }
    }

    SideEffect {
        coroutine.launch {
            effect.collect() { effect ->
                when (effect) {

                    else -> {}
                }
            }
        }
    }

}


