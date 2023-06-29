package com.honey.designcolorpalette.ui.screen.harmony.contract

import com.honey.designcolorpalette.base.ViewState

sealed class HarmonyState : ViewState {
    object Loading : HarmonyState()
    object Show : HarmonyState()
}
