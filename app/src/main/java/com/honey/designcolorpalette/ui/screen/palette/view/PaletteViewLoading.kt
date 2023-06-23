package com.honey.designcolorpalette.ui.screen.palette.view

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState

@Composable
fun PaletteViewLoading(
    state: PaletteState.Loading
) {
    CircularProgressIndicator()
}