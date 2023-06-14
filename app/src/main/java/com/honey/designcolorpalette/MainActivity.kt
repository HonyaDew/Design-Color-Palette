package com.honey.designcolorpalette

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.honey.designcolorpalette.extencion.string
import com.honey.designcolorpalette.ui.main.DcpApp
import com.honey.designcolorpalette.ui.theme.DcpTheme
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.Palette
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

lateinit var showSettingsState: MutableStateFlow<Boolean>

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            showSettingsState = MutableStateFlow(false)

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

//private fun main(){
//    //simple convert colors to DcpColorString
//    println("--50 ${Color(0xFF).string()}")
//}






