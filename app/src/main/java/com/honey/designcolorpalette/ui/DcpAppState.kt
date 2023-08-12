package com.honey.designcolorpalette.ui

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
import com.honey.designcolorpalette.ui.navigation.TopLevelDestination
import com.honyadew.harmony.navigation.navigateToHarmony
import com.honyadew.image.navigation.navigateToImage
import com.honyadew.palette.navigation.navigateToPalette
import com.honyadew.palette.showSettingsInfo
import com.honyadew.saved.navigation.navigateToSaved
import com.honyadew.sliders.navigation.navigateToSliders
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
            com.honyadew.palette.navigation.paletteRoute -> TopLevelDestination.PALETTE
            com.honyadew.sliders.navigation.slidersRoute -> TopLevelDestination.SLIDERS
            com.honyadew.saved.navigation.savedRoute -> TopLevelDestination.SAVED
            com.honyadew.harmony.navigation.harmonyRoute -> TopLevelDestination.HARMONY
            com.honyadew.image.navigation.imageRoute -> TopLevelDestination.IMAGE
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
        showSettingsInfo.value = show
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
