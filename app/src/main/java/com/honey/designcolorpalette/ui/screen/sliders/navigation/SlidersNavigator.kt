package com.honey.designcolorpalette.ui.screen.sliders.navigation

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val slidersRoute = "sliders_route"

fun NavController.navigateToSliders(navOptions: NavOptions? = null){
    this.navigate(slidersRoute, navOptions)
}

fun NavGraphBuilder.slidersScreen(onColorClick : (color: Color)-> Unit){
    composable(route = slidersRoute){
        SlidersRoute(onColorClick = onColorClick)
    }
}