package com.honyadew.palette.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honyadew.model.ColorInfo

const val paletteRoute = "palette_route"

fun NavController.navigateToPalette(navOptions: NavOptions? = null){
    this.navigate(paletteRoute, navOptions)
}

fun NavGraphBuilder.paletteScreen(onColorClick : (color: com.honyadew.model.ColorInfo) -> Unit){
    composable(route = paletteRoute){
        PaletteRoute(onColorClick = onColorClick)
    }
}