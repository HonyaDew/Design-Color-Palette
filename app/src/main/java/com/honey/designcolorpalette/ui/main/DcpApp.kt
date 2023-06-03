package com.honey.designcolorpalette.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.honey.designcolorpalette.ui.main.navigation.DcpNavHost
import com.honey.designcolorpalette.ui.main.view.DcpBackground
import com.honey.designcolorpalette.ui.main.view.DcpBottomBar
import com.honey.designcolorpalette.ui.main.view.DcpNavRail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DcpApp(
    windowSizeClass: WindowSizeClass,
    appState: DcpAppState = rememberDcpAppState(windowSizeClass = windowSizeClass)
){
    DcpBackground() {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = {/*TODO*/},
            bottomBar = {
                if (appState.showBottomBar){
                    DcpBottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination
                    )
                }
            }
        ){padding ->
            //NavRail > TopBar priority, so it's here.
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                if (appState.showNavRail){
                    DcpNavRail(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination
                    )
                }
                Column {
                    val destination = appState.currentTopLevelDestination
                    if (destination != null){
                        CenterAlignedTopAppBar(
                            title = { Text(text = stringResource(id = destination.titleId)) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent
                            )
                        )
                    }

                    DcpNavHost(
                        navController = appState.navController
                    )
                }
            }
        }
    }
}


