package com.honey.designcolorpalette.ui.screen.color.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.honey.domain.model.ColorInfo

const val colorRoute = "color_route"
const val colorInfoArg = "color_info"


fun NavController.navigateToColor(color: ColorInfo){
    this.navigate("$colorRoute/$color")
}

fun NavGraphBuilder.colorScreen(){
    composable(
        route = "$colorRoute/{$colorInfoArg}",
        arguments = listOf(
            navArgument(colorInfoArg) { type = NavType.StringType}
        )
    ){
        ColorRoute()
    }
}