package com.honyadew.image.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honyadew.model.ColorInfo

const val imageRoute = "image_route"

fun NavController.navigateToImage(navOptions: NavOptions? = null){
    this.navigate(imageRoute, navOptions)
}

fun NavGraphBuilder.imageScreen(onColorClick: (color: com.honyadew.model.ColorInfo) -> Unit){
    composable(route = imageRoute){
        ImageRoute(onColorClick = onColorClick)
    }
}