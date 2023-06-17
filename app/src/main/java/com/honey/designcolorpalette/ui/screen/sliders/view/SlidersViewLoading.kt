package com.honey.designcolorpalette.ui.screen.sliders.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.honey.designcolorpalette.ui.screen.sliders.contract.SlidersState

@Composable
fun SlidersViewLoading(
    state: SlidersState.Loading
) {
    CircularProgressIndicator()
}