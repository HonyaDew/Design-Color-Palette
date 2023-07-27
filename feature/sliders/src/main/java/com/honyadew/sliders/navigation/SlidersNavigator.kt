package com.honyadew.sliders.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honyadew.model.ColorInfo

const val slidersRoute = "sliders_route"

fun NavController.navigateToSliders(navOptions: NavOptions? = null){
    this.navigate(slidersRoute, navOptions)
}

fun NavGraphBuilder.slidersScreen(onColorClick : (color: com.honyadew.model.ColorInfo)-> Unit){
    composable(route = slidersRoute){
        SlidersRoute(onColorClick = onColorClick)
    }
}