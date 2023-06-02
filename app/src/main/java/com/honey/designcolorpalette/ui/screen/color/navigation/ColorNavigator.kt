package com.honey.designcolorpalette.ui.screen.color.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val colorRoute = "color_route"

fun NavController.navigateToColor(navOptions: NavOptions? = null){
    this.navigate(colorRoute, navOptions)
}

fun NavGraphBuilder.colorScreen(){
    composable(route = colorRoute){
        ColorRoute()
    }
}