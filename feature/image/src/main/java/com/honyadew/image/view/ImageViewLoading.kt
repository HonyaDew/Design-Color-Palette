package com.honyadew.image.view

import androidx.compose.foundation.background
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.honyadew.image.contract.ImageState
import com.honyadew.designsystem.theme.colorSelect

@Composable
fun ImageViewLoading(
    state: ImageState.Loading
) {
    CircularProgressIndicator(Modifier.background(colorSelect(inverse = true)))
}