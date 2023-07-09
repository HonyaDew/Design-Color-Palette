package com.honey.designcolorpalette.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.honey.designcolorpalette.main.isDarkTheme

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

//Using this color select only for reason - we can use only black and white
@Composable
fun colorSelect(
    saturation: Int = 100,
    inverse : Boolean = false,
    darkTheme: Boolean = isDarkTheme
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}