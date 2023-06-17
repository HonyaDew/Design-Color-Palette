package com.honey.designcolorpalette.ui.screen.saved.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.honey.designcolorpalette.ui.screen.saved.contact.SavedState

@Composable
fun SavedViewLoading(state: SavedState.Loading){
    CircularProgressIndicator()
}