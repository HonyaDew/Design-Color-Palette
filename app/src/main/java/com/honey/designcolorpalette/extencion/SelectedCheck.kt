package com.honey.designcolorpalette.extencion

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.honey.designcolorpalette.ui.main.navigation.TopLevelDestination

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
