package com.honey.designcolorpalette.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.honyadew.harmony.navigation.harmonyScreen
import com.honyadew.image.navigation.imageScreen
import com.honyadew.palette.navigation.paletteRoute
import com.honyadew.palette.navigation.paletteScreen
import com.honyadew.saved.navigation.savedScreen
import com.honyadew.sliders.navigation.slidersScreen

@Composable
fun DcpNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = com.honyadew.palette.navigation.paletteRoute,
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