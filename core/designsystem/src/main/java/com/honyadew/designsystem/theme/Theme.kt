package com.honyadew.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import kotlinx.coroutines.flow.MutableStateFlow

private val DarkColorScheme = darkColorScheme(
    primary = Color.Black,
    primaryContainer = Color.White,
    onPrimary = Color.White,
    onPrimaryContainer = Color.Black,
    background = Color.Black,
    onBackground = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color.White,
    primaryContainer = Color.Black,
    onPrimary = Color.Black,
    onPrimaryContainer = Color.White,
    background = Color.White,
    onBackground = Color.Black
)

var isDarkThemeColorSelect : Boolean = false
//Using this color select only for reason - we can use only black and white
@Composable
fun colorSelect(
    saturation: Int = 100,
    inverse : Boolean = false,
    darkTheme: Boolean = isDarkThemeColorSelect
): Color{
    val dark = if (inverse) !darkTheme else darkTheme
    return if (!dark){
        when(saturation){
            90 -> {White90}
            80 -> {White80}
            70 -> {White70}
            else -> {Color.White}
        }
    } else {
        when(saturation){
            90-> {Black90}
            80-> {Black80}
            70-> {Black70}
            else -> {Color.Black}
        }
    }
}


@Composable
fun DcpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}