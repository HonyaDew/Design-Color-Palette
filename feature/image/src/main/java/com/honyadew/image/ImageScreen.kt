package com.honyadew.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honyadew.image.contract.ImageEffect
import com.honyadew.image.contract.ImageEvent
import com.honyadew.image.contract.ImageState
import com.honyadew.image.view.ImageViewLoading
import com.honyadew.image.view.ImageViewShow
import com.honyadew.model.ColorInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun ImageScreen(
    state: State<ImageState>,
    effect: SharedFlow<ImageEffect?>,
    onEventSend: (event: ImageEvent) -> Unit,
    onColorClick: (color: com.honyadew.model.ColorInfo) -> Unit,
    coroutine : CoroutineScope = rememberCoroutineScope()
) {
    when(val state = state.value){
        is ImageState.Loading -> {
            ImageViewLoading(state = state)
        }
        is ImageState.Show -> {
            ImageViewShow(
                state = state,
                onSaveColorScheme = {colorScheme ->
                    onEventSend.invoke(ImageEvent.SaveColorScheme(colorScheme))
                },
                onRemoveFromToSave = { colorInfo ->
                    onEventSend.invoke(ImageEvent.RemoveFromToSave(colorInfo))
                },
                onMoveToSave = { colorInfo ->
                    onEventSend.invoke(ImageEvent.MoveToSave(colorInfo))
                },
                onChangeSelectedColor = {color ->
                    onEventSend.invoke(ImageEvent.SelectColor(color))
                },
                onExtractColor = { extractedColors ->
                    onEventSend.invoke(ImageEvent.SetExtractedColors(extractedColors))
                },
                onSetBitmap = { bitmap ->
                    onEventSend.invoke(ImageEvent.SetBitmap(bitmap))
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