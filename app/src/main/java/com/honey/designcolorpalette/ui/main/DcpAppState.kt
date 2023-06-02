package com.honey.designcolorpalette.ui.main

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.honey.designcolorpalette.ui.main.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDcpAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navHostController: NavHostController = rememberNavController(),
) : DcpAppState{
    return remember(navHostController, coroutineScope, windowSizeClass){
        DcpAppState(navHostController, coroutineScope, windowSizeClass)
    }
}

@Stable
class DcpAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

//    val currentTopLevelDestination: TopLevelDestination?
//        @Composable get() = when (currentDestination?.route) {
//            forYouNavigationRoute -> FOR_YOU
//            bookmarksRoute -> BOOKMARKS
//            interestsRoute -> INTERESTS
//            else -> null
//        }
}