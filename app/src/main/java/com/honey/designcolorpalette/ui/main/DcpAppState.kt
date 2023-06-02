package com.honey.designcolorpalette.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDcpAppState() : DcpAppState{
    TODO()
}


class DcpAppState(
    val coroutineScope: CoroutineScope,
    val navController: NavHostController
) {
    //TODO
}