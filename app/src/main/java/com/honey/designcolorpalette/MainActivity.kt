package com.honey.designcolorpalette

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.honey.designcolorpalette.ui.DcpApp
import com.honyadew.designsystem.theme.DcpTheme
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.designsystem.theme.isDarkThemeColorSelect
import com.honyadew.designsystem.view.DcpColorCard
import com.honyadew.extencion.string
import com.honyadew.model.ColorInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

//TODO maybe should find another way
var isDarkTheme : Boolean = false
val showSettingsState = MutableStateFlow(false)

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

                    LightStatusBar(darkTheme = isDarkTheme)

                    isDarkThemeColorSelect = isDarkTheme

                    DcpTheme(darkTheme = isDarkTheme) {
                        DcpApp(
                            windowSizeClass = calculateWindowSizeClass(this)
                        )
//                        Box(modifier = Modifier.size(128.dp).background(colorSelect()))
                    }
                }
            }
        }
    }
}

@Composable
fun LightStatusBar(
    darkTheme : Boolean
){
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            //TODO
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }
}

@Composable
fun shouldDarkTheme(
    state: MainActivityState
) : Boolean = when(state){
    MainActivityState.Loading -> isSystemInDarkTheme()
    is MainActivityState.Show -> when(state.editableSettings.themeConfig){
        com.honyadew.model.ThemeConfig.LIGHT -> false
        com.honyadew.model.ThemeConfig.DARK -> true
        com.honyadew.model.ThemeConfig.DEFAULT -> isSystemInDarkTheme()
    }
}

//private fun main(){
//    //simple convert colors to DcpColorString
//    println("--50 ${Color(0xFF).string()}")
//}









