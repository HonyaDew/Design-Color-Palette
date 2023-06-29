package com.honey.designcolorpalette.ui.screen.harmony.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.honey.designcolorpalette.ui.screen.harmony.contract.HarmonyState
import com.honey.designcolorpalette.ui.theme.colorSelect

@Composable
fun HarmonyViewLoading(
    state: HarmonyState.Loading
) {
    CircularProgressIndicator(color = colorSelect(saturation = 70))
}