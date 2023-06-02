package com.honey.designcolorpalette.ui.screen.sliders

import com.honey.designcolorpalette.base.BaseViewModel
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEffect
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersEvent
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersState

class SlidersViewModel : BaseViewModel<SlidersEvent, SlidersState, SlidersEffect>(initialState = SlidersState.Loading){
    override fun obtainEvent(event: SlidersEvent) {

    }

}
