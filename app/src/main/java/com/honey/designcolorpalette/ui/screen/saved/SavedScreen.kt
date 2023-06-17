package com.honey.designcolorpalette.ui.screen.saved

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEffect
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedEvent
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedState
import com.honey.designcolorpalette.ui.screen.saved.view.SavedViewLoading
import com.honey.domain.model.ColorInfo
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