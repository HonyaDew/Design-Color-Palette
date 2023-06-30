package com.honey.designcolorpalette.ui.screen.image.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.domain.model.ColorInfo

const val imageRoute = "image_route"

fun NavController.navigateToImage(navOptions: NavOptions? = null){
    this.navigate(imageRoute, navOptions)
}

fun NavGraphBuilder.imageScreen(onColorClick: (color: ColorInfo) -> Unit){
    composable(route = imageRoute){
        ImageRoute(onColorClick = onColorClick)
    }
}