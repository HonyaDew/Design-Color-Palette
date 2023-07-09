package com.honey.designcolorpalette.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.honey.designcolorpalette.ui.main.DcpApp
import com.honey.designcolorpalette.ui.theme.DcpTheme
import com.honey.domain.model.ThemeConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

val showSettingsState = MutableStateFlow(false)
var isDarkTheme : Boolean = false

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var state: MainActivityState by mutableStateOf(MainActivityState.Loading)

        lifecycleScope.launch {
            viewModel.viewState.collect{ newState ->
                state = newState
            }

        }

        when(state){
            MainActivityState.Loading -> {splashScreen.setKeepOnScreenCondition{true}}
            is MainActivityState.Show -> {
                setContent {
                    isDarkTheme = shouldDarkTheme(state = state)

                    val systemUiController = rememberSystemUiController()
                    systemUiController.setSystemBarsColor(Color.Transparent)

                    WindowCompat.setDecorFitsSystemWindows(window, false)

                    DcpTheme(darkTheme = isDarkTheme) {
                        DcpApp(
                            windowSizeClass = calculateWindowSizeClass(this)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun shouldDarkTheme(
    state: MainActivityState
) : Boolean = when(state){
    MainActivityState.Loading -> isSystemInDarkTheme()
    is MainActivityState.Show -> when(state.editableSettings.themeConfig){
        ThemeConfig.LIGHT -> false
        ThemeConfig.DARK -> true
        ThemeConfig.DEFAULT -> isSystemInDarkTheme()
    }
}

//private fun main(){
//    //simple convert colors to DcpColorString
//    println("--50 ${Color(0xFF).string()}")
//}









