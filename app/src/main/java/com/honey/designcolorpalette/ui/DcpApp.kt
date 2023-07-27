package com.honey.designcolorpalette.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.ui.navigation.DcpNavHost
import com.honey.designcolorpalette.ui.navigation.TopLevelDestination
import com.honyadew.designsystem.view.DcpBackground

val LocalActiveColor = compositionLocalOf<Boolean> { false }

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DcpApp(
    windowSizeClass: WindowSizeClass,
    appState: DcpAppState = rememberDcpAppState(windowSizeClass = windowSizeClass)
){
    DcpBackground {

        if (appState.showSettingsDialog.value){
            com.honyadew.settings.SettingsDialogRoute(onDismiss = {
                appState.setShowSettingsDialog(
                    false
                )
            })
        }

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
            CompositionLocalProvider(
                LocalActiveColor provides appState.showSettingsDialog.value
            ) {
                //NavRail > TopBar priority, so second here.
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
                                ),
                                actions = {
                                    IconButton(onClick = { appState.setShowSettingsDialog(true) }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_settings),
                                            contentDescription = "Settings"
                                        )
                                    }
                                }
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
}

@Composable
fun DcpNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (destination : TopLevelDestination)-> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = Color.Transparent
    ) {
        destinations.forEach {destination->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination.invoke(destination) },
                icon = {
                    val iconResId = if (selected){
                        destination.selectedButtonId
                    } else {
                        destination.unSelectedButtonId
                    }
                    Icon(painter = painterResource(id = iconResId), contentDescription = "Icon")
                },
                label = { Text(text = stringResource(id = destination.iconTextId))}
            )
        }
    }
}

@Composable
fun DcpBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (destination: TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    BottomAppBar(modifier = modifier) {
        destinations.forEach { destination->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination.invoke(destination) },
                icon = {
                    val iconResId = if (selected){
                        destination.selectedButtonId
                    } else {
                        destination.unSelectedButtonId
                    }
                    Icon(painter = painterResource(id = iconResId), contentDescription = "Icon")
                },
                label = { Text(text = stringResource(id = destination.iconTextId)) }
            )
        }
    }

}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false


