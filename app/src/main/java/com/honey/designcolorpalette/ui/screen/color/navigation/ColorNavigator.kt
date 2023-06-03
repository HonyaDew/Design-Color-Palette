package com.honey.designcolorpalette.ui.screen.color.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable

const val colorRoute = "color_route"
const val colorString = "color_string"


fun NavController.navigateToColor(color: String){
    this.navigate("$colorRoute/$color")
}

fun NavGraphBuilder.colorScreen(){
    composable(
        route = "$colorRoute/{$colorString}",
        arguments = listOf(
            navArgument(colorString) { type = NavType.StringType}
        )
    ){
        ColorRoute()
    }
}