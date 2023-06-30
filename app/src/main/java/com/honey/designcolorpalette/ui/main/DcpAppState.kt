package com.honey.designcolorpalette.ui.main

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.honey.designcolorpalette.showSettingsState
import com.honey.designcolorpalette.ui.main.navigation.TopLevelDestination
import com.honey.designcolorpalette.ui.screen.harmony.navigation.harmonyRoute
import com.honey.designcolorpalette.ui.screen.harmony.navigation.navigateToHarmony
import com.honey.designcolorpalette.ui.screen.image.navigation.imageRoute
import com.honey.designcolorpalette.ui.screen.image.navigation.navigateToImage
import com.honey.designcolorpalette.ui.screen.palette.navigation.navigateToPalette
import com.honey.designcolorpalette.ui.screen.palette.navigation.paletteRoute
import com.honey.designcolorpalette.ui.screen.saved.navigation.navigateToSaved
import com.honey.designcolorpalette.ui.screen.saved.navigation.savedRoute
import com.honey.designcolorpalette.ui.screen.sliders.navigation.navigateToSliders
import com.honey.designcolorpalette.ui.screen.sliders.navigation.slidersRoute
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDcpAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): DcpAppState {
    return remember(navController, coroutineScope, windowSizeClass) {
        DcpAppState(navController, coroutineScope, windowSizeClass)
    }
}

@Stable
class DcpAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            paletteRoute -> TopLevelDestination.PALETTE
            slidersRoute -> TopLevelDestination.SLIDERS
            savedRoute -> TopLevelDestination.SAVED
            harmonyRoute -> TopLevelDestination.HARMONY
            imageRoute -> TopLevelDestination.IMAGE
            else -> null
        }

    val showBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val showNavRail: Boolean
        get() = !showBottomBar

    //TODO idk this looks ugly and I don't know for what
    private val _showSettingsDialog = mutableStateOf(false)
    val showSettingsDialog : State<Boolean> = _showSettingsDialog
    fun setShowSettingsDialog(show: Boolean) {
        _showSettingsDialog.value = show
        showSettingsState.value = show
    }


    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.PALETTE -> navController.navigateToPalette(topLevelNavOptions)
            TopLevelDestination.SLIDERS -> navController.navigateToSliders(topLevelNavOptions)
            TopLevelDestination.SAVED -> navController.navigateToSaved(topLevelNavOptions)
            TopLevelDestination.HARMONY -> navController.navigateToHarmony(topLevelNavOptions)
            TopLevelDestination.IMAGE -> navController.navigateToImage(topLevelNavOptions)
        }
    }
}

