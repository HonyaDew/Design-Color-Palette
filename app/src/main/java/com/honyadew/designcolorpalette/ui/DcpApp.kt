package com.honyadew.designcolorpalette.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.honyadew.GlobalSignals
import com.honyadew.designcolorpalette.R
import com.honyadew.designcolorpalette.ui.navigation.DcpNavHost
import com.honyadew.designcolorpalette.ui.navigation.TopLevelDestination
import com.honyadew.designsystem.view.DcpBackground
import com.honyadew.settings.SettingsDialogRoute

val LocalActiveColor = compositionLocalOf<Boolean> { false }

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DcpApp(
    windowSizeClass: WindowSizeClass,
    appState: DcpAppState = rememberDcpAppState(windowSizeClass = windowSizeClass)
){
    val snackbarHostState = remember {SnackbarHostState()}
    val snackbarText = GlobalSignals.snackbarHostState.collectAsState()
    LaunchedEffect(snackbarText.value) {
        if (snackbarText.value.isNotEmpty()){
            snackbarHostState.showSnackbar(
                message = snackbarText.value,
                duration = SnackbarDuration.Short
            )
            GlobalSignals.snackbarHostState.emit("")
        }
    }

    DcpBackground {
        if (appState.showSettingsDialog.value){
            SettingsDialogRoute(onDismiss = {
                appState.setShowSettingsDialog(
                    false
                )
            })
        }

        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
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

@Composable
fun DcpSnackbar(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(hostState = snackbarHostState)
}

