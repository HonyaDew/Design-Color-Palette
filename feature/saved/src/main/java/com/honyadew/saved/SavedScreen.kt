package com.honyadew.saved

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honyadew.saved.contact.SavedEffect
import com.honyadew.saved.contact.SavedEvent
import com.honyadew.saved.contact.SavedState
import com.honyadew.saved.view.SavedViewLoading
import com.honyadew.saved.view.SavedViewShow
import com.honyadew.model.ColorInfo
import com.honyadew.saved.view.SavedViewNoObjects
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
        is SavedState.NoObjects -> {
            SavedViewNoObjects(state = state)
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


