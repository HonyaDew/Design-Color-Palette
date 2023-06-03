package com.honey.designcolorpalette.ui.main.view

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.honey.designcolorpalette.extencion.isTopLevelDestinationInHierarchy
import com.honey.designcolorpalette.ui.main.navigation.TopLevelDestination

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