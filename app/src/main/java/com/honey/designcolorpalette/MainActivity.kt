package com.honey.designcolorpalette

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.honey.designcolorpalette.ui.main.DcpApp
import com.honey.designcolorpalette.ui.theme.DcpTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Color.Transparent)

            WindowCompat.setDecorFitsSystemWindows(window, false)

            DcpTheme {
                DcpApp(
                    windowSizeClass = calculateWindowSizeClass(this)
                )
            }
        }
    }
}




