package com.honey.designcolorpalette.ui.screen.palette.navigation

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.designcolorpalette.model.ColorInfo
import com.honey.designcolorpalette.ui.screen.palette.PaletteScreen

const val paletteRoute = "palette_route"

fun NavController.navigateToPalette(navOptions: NavOptions? = null){
    this.navigate(paletteRoute, navOptions)
}

fun NavGraphBuilder.paletteScreen(onColorClick : (color: ColorInfo) -> Unit){
    composable(route = paletteRoute){
        PaletteRoute(onColorClick = onColorClick)
    }
}