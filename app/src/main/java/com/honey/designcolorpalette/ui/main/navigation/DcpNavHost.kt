package com.honey.designcolorpalette.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.honey.designcolorpalette.ui.screen.harmony.navigation.harmonyScreen
import com.honey.designcolorpalette.ui.screen.image.navigation.imageScreen
import com.honey.designcolorpalette.ui.screen.palette.navigation.paletteRoute
import com.honey.designcolorpalette.ui.screen.palette.navigation.paletteScreen
import com.honey.designcolorpalette.ui.screen.saved.navigation.savedScreen
import com.honey.designcolorpalette.ui.screen.sliders.navigation.slidersScreen

@Composable
fun DcpNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = paletteRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        paletteScreen(
            onColorClick = {color -> }
        )
        slidersScreen(
            onColorClick = {color -> }
        )
        savedScreen (
            onColorClick = {color -> }
        )
        harmonyScreen (
            onColorClick = {color -> }
        )
        imageScreen (
            onColorClick = {color -> }
        )
    }
}