package com.honey.designcolorpalette.ui.screen.image

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEffect
import com.honey.designcolorpalette.ui.screen.image.contract.ImageEvent
import com.honey.designcolorpalette.ui.screen.image.contract.ImageState

class ImageViewModel : BaseViewModel<ImageEvent, ImageState, ImageEffect>(initialState = ImageState.Loading) {
    override fun obtainEvent(event: ImageEvent) {
        TODO("Not yet implemented")
    }
}