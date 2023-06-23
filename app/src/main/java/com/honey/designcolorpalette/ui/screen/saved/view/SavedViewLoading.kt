package com.honey.designcolorpalette.ui.screen.saved.view

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedState
import com.honey.designcolorpalette.ui.theme.colorSelect

@Composable
fun SavedViewLoading(state: SavedState.Loading){
    CircularProgressIndicator(color = colorSelect(saturation = 90, inverse = true))
}