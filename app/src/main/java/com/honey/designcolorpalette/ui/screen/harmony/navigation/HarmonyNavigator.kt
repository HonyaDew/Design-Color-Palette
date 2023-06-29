package com.honey.designcolorpalette.ui.screen.harmony.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.honey.designcolorpalette.ui.screen.palette.navigation.paletteRoute
import com.honey.domain.model.ColorInfo

const val harmonyRoute = "harmony_route"

fun NavController.navigateToHarmony(navOptions: NavOptions? = null){
    this.navigate(harmonyRoute, navOptions)
}

fun NavGraphBuilder.harmonyScreen(onColorClick: (color:ColorInfo) -> Unit){
    composable(route = harmonyRoute){
        HarmonyRoute(onColorClick = onColorClick)
    }
}