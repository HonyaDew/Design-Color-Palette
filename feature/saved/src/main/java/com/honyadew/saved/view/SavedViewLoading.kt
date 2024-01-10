package com.honyadew.saved.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.honyadew.saved.contract.SavedState
import com.honyadew.designsystem.theme.colorSelect

@Composable
fun SavedViewLoading(state: SavedState.Loading){
    CircularProgressIndicator(color = colorSelect(saturation = 90, inverse = true))
}