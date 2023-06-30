package com.honey.designcolorpalette.ui.screen.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEffect
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEvent
import com.honey.designcolorpalette.ui.screen.image.contract.ImageState
import com.honey.designcolorpalette.ui.screen.image.view.ImageViewLoading
import com.honey.domain.model.ColorInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun ImageScreen(
    state: State<ImageState>,
    effect: SharedFlow<ImageEffect?>,
    onEventSend: (event: ImageEvent) -> Unit,
    onColorClick: (color: ColorInfo) -> Unit,
    coroutine : CoroutineScope = rememberCoroutineScope()
) {
    when(val state = state.value){
        is ImageState.Loading -> {
            ImageViewLoading(state = state)
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