package com.honey.designcolorpalette.ui.screen.image.contract

import com.honey.designcolorpalette.base.ViewState

sealed class ImageState : ViewState {
    object Loading : ImageState()
}