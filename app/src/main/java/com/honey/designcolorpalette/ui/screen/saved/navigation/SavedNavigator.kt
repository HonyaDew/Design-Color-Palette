package com.honey.designcolorpalette.ui.screen.saved.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.domain.model.ColorInfo

const val savedRoute = "saved_route"

fun NavController.navigateToSaved(navOptions: NavOptions? = null) {
    this.navigate(savedRoute, navOptions)
}

fun NavGraphBuilder.savedScreen(onColorClick: (color: ColorInfo) -> Unit){
    composable(route = savedRoute){
        SavedRoute(onColorClick = onColorClick)
    }
}