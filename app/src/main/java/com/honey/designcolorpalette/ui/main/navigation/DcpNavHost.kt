package com.honey.designcolorpalette.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.honey.designcolorpalette.ui.screen.color.navigation.colorScreen
import com.honey.designcolorpalette.ui.screen.color.navigation.navigateToColor
import com.honey.designcolorpalette.ui.screen.palette.navigation.paletteRoute
import com.honey.designcolorpalette.ui.screen.palette.navigation.paletteScreen
import com.honey.designcolorpalette.ui.screen.sliders.navigation.slidersScreen

@Composable
fun DcpNavHost(
    navController:NavHostController,
    modifier : Modifier = Modifier,
    startDestination: String = paletteRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        paletteScreen(
            onColorClick = {color ->
                navController.navigateToColor(color = color)
            }
        )
        slidersScreen(
            onColorClick = {color ->
                navController.navigateToColor(color = color)
            }
        )
        //Not sure for this good idea to put not top-level-destination here, but anyway TODO
        colorScreen()

    }
}