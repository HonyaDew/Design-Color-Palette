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

val MaterialRed = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFFEBEE).string(), name = "50", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFFFCDD2).string(), name = "100", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFEF9A9A).string(), name = "200", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFE57373).string(), name = "300", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFEF5350).string(), name = "400", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFF44336).string(), name = "500", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFE53935).string(), name = "600", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFD32F2F).string(), name = "700", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFC62828).string(), name = "800", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFB71C1C).string(), name = "900", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFFF8A80).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFFF5252).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFFF1744).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.RED)),
    ColorInfo(value = Color(0xFFD50000).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.RED)),
)
val MaterialPink = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFCE4EC).string(), name = "50", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFF8BBD0).string(), name = "100", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFF48FB1).string(), name = "200", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFF06292).string(), name = "300", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFEC407A).string(), name = "400", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFE91E63).string(), name = "500", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFD81B60).string(), name = "600", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFC2185B).string(), name = "700", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFAD1457).string(), name = "800", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFF880E4F).string(), name = "900", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFFF80AB).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFFF4081).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFF50057).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.PINK)),
    ColorInfo(value = Color(0xFFC51162).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.PINK)),
)
val MaterialPurple = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFF3E5F5).string(), name = "50", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFE1BEE7).string(), name = "100", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFCE93D8).string(), name = "200", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFBA68C8).string(), name = "300", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFAB47BC).string(), name = "400", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFF9C27B0).string(), name = "500", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFF8E24AA).string(), name = "600", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFF7B1FA2).string(), name = "700", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFF6A1B9A).string(), name = "800", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFF4A148C).string(), name = "900", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFEA80FC).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFE040FB).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFD500F9).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.PURPLE)),
    ColorInfo(value = Color(0xFFAA00FF).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.PURPLE)),
)
val MaterialDeepPurple = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFEDE7F6).string(), name = "50", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFFD1C4E9).string(), name = "100", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFFB39DDB).string(), name = "200", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF9575CD).string(), name = "300", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF7E57C2).string(), name = "400", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF673AB7).string(), name = "500", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF5E35B1).string(), name = "600", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF512DA8).string(), name = "700", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF4527A0).string(), name = "800", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF4A148C).string(), name = "900", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFFB388FF).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF7C4DFF).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF651FFF).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
    ColorInfo(value = Color(0xFF6200EA).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.DEEP_PURPLE)),
)
val MaterialIndigo = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFE8EAF6).string(), name = "50", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFFC5CAE9).string(), name = "100", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF9FA8DA).string(), name = "200", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF7986CB).string(), name = "300", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF5C6BC0).string(), name = "400", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF3F51B5).string(), name = "500", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF3949AB).string(), name = "600", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF303F9F).string(), name = "700", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF283593).string(), name = "800", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF1A237E).string(), name = "900", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF8C9EFF).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF536DFE).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF3D5AFE).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.INDIGO)),
    ColorInfo(value = Color(0xFF304FFE).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.INDIGO)),
)
val MaterialBlue = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFE3F2FD).string(), name = "50", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFFBBDEFB).string(), name = "100", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF90CAF9).string(), name = "200", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF64B5F6).string(), name = "300", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF42A5F5).string(), name = "400", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF2196F3).string(), name = "500", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF1E88E5).string(), name = "600", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF1976D2).string(), name = "700", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF1565C0).string(), name = "800", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF0D47A1).string(), name = "900", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF82B1FF).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF448AFF).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF2979FF).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.BLUE)),
    ColorInfo(value = Color(0xFF2962FF).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.BLUE)),
)
val MaterialLightBlue = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFE1F5FE).string(), name = "50", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFFB3E5FC).string(), name = "100", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF81D4FA).string(), name = "200", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF4FC3F7).string(), name = "300", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF29B6F6).string(), name = "400", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF03A9F4).string(), name = "500", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF039BE5).string(), name = "600", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF0288D1).string(), name = "700", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF0277BD).string(), name = "800", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF01579B).string(), name = "900", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF80D8FF).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF40C4FF).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF00B0FF).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
    ColorInfo(value = Color(0xFF0091EA).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.LIGHT_BLUE)),
)
val MaterialCyan = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFE0F7FA).string(), name = "50", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFFB2EBF2).string(), name = "100", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF80DEEA).string(), name = "200", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF4DD0E1).string(), name = "300", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF26C6DA).string(), name = "400", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF00BCD4).string(), name = "500", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF00ACC1).string(), name = "600", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF0097A7).string(), name = "700", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF00838F).string(), name = "800", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF006064).string(), name = "900", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF84FFFF).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF18FFFF).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF00E5FF).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.CYAN)),
    ColorInfo(value = Color(0xFF00B8D4).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.CYAN)),
)

val MaterialTeal = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFE0F2F1).string(), name = "50", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFFB2DFDB).string(), name = "100", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF80CBC4).string(), name = "200", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF4DB6AC).string(), name = "300", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF26A69A).string(), name = "400", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF009688).string(), name = "500", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF00897B).string(), name = "600", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF00796B).string(), name = "700", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF00695C).string(), name = "800", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF004D40).string(), name = "900", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFFA7FFEB).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF64FFDA).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF1DE9B6).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.TEAL)),
    ColorInfo(value = Color(0xFF00BFA5).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.TEAL)),
)

val MaterialGreen = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFE8F5E9).string(), name = "50", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFFC8E6C9).string(), name = "100", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFFA5D6A7).string(), name = "200", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF81C784).string(), name = "300", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF66BB6A).string(), name = "400", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF4CAF50).string(), name = "500", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF43A047).string(), name = "600", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF388E3C).string(), name = "700", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF2E7D32).string(), name = "800", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF004D40).string(), name = "900", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFFB9F6CA).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF64FFDA).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF00E676).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.GREEN)),
    ColorInfo(value = Color(0xFF00C853).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.GREEN)),
)

val MaterialLightGreen = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFF1F8E9).string(), name = "50", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFFDCEDC8).string(), name = "100", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFFC5E1A5).string(), name = "200", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFFAED581).string(), name = "300", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF9CCC65).string(), name = "400", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF8BC34A).string(), name = "500", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF7CB342).string(), name = "600", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF689F38).string(), name = "700", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF558B2F).string(), name = "800", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF33691E).string(), name = "900", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFFCCFF90).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFFB2FF59).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF76FF03).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
    ColorInfo(value = Color(0xFF64DD17).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.LIGHT_GREEN)),
)

val MaterialLime = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFF9FBE7).string(), name = "50", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFF0F4C3).string(), name = "100", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFE6EE9C).string(), name = "200", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFDCE775).string(), name = "300", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFD4E157).string(), name = "400", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFCDDC39).string(), name = "500", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFC0CA33).string(), name = "600", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFAFB42B).string(), name = "700", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFF9E9D24).string(), name = "800", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFF827717).string(), name = "900", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFF4FF81).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFEEFF41).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFC6FF00).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.LIME)),
    ColorInfo(value = Color(0xFFAEEA00).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.LIME)),
)

val MaterialYellow = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFFFDE7).string(), name = "50", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFF9C4).string(), name = "100", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFF59D).string(), name = "200", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFF176).string(), name = "300", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFEE58).string(), name = "400", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFEB3B).string(), name = "500", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFDD835).string(), name = "600", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFBC02D).string(), name = "700", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFF9A825).string(), name = "800", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFF57F17).string(), name = "900", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFFF8D).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFFF00).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFEA00).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.YELLOW)),
    ColorInfo(value = Color(0xFFFFD600).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.YELLOW)),
)

val MaterialAmber = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFFF8E1).string(), name = "50", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFECB3).string(), name = "100", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFE082).string(), name = "200", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFD54F).string(), name = "300", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFCA28).string(), name = "400", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFC107).string(), name = "500", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFB300).string(), name = "600", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFA000).string(), name = "700", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFA000).string(), name = "800", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFF8F00).string(), name = "900", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFE57F).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFD740).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFC400).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.AMBER)),
    ColorInfo(value = Color(0xFFFFAB00).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.AMBER)),
)

val MaterialOrange = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFFF3E0).string(), name = "50", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFFE0B2).string(), name = "100", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFFCC80).string(), name = "200", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFFB74D).string(), name = "300", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFFA726).string(), name = "400", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFF9800).string(), name = "500", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFB8C00).string(), name = "600", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFF57C00).string(), name = "700", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFEF6C00).string(), name = "800", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFE65100).string(), name = "900", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFFD180).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFFAB40).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFF9100).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.ORANGE)),
    ColorInfo(value = Color(0xFFFF6D00).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.ORANGE)),
)

val MaterialDeepOrange = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFBE9E7).string(), name = "50", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFFCCBC).string(), name = "100", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFFAB91).string(), name = "200", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFF8A65).string(), name = "300", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFF7043).string(), name = "400", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFF5722).string(), name = "500", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFF4511E).string(), name = "600", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFE64A19).string(), name = "700", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFD84315).string(), name = "800", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFBF360C).string(), name = "900", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFF9E80).string(), name = "A100", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFF6E40).string(), name = "A200", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFFF3D00).string(), name = "A400", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
    ColorInfo(value = Color(0xFFDD2C00).string(), name = "A700", palette = Palette.Material(ColorOfMaterial.DEEP_ORANGE)),
)
val MaterialBrown = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFEFEBE9).string(), name = "50", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFFD7CCC8).string(), name = "100", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFFBCAAA4).string(), name = "200", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFFA1887F).string(), name = "300", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFF8D6E63).string(), name = "400", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFF795548).string(), name = "500", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFF6D4C41).string(), name = "600", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFF5D4037).string(), name = "700", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFF4E342E).string(), name = "800", palette = Palette.Material(ColorOfMaterial.BROWN)),
    ColorInfo(value = Color(0xFF3E2723).string(), name = "900", palette = Palette.Material(ColorOfMaterial.BROWN)),
)
val MaterialGrey = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFAFAFA).string(), name = "50", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFFF5F5F5).string(), name = "100", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFFEEEEEE).string(), name = "200", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFFE0E0E0).string(), name = "300", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFFBDBDBD).string(), name = "400", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFF9E9E9E).string(), name = "500", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFF757575).string(), name = "600", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFF616161).string(), name = "700", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFF424242).string(), name = "800", palette = Palette.Material(ColorOfMaterial.GREY)),
    ColorInfo(value = Color(0xFF212121).string(), name = "900", palette = Palette.Material(ColorOfMaterial.GREY)),
)
val MaterialBlueGray = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFECEFF1).string(), name = "50", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFFCFD8DC).string(), name = "100", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFFB0BEC5).string(), name = "200", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFF90A4AE).string(), name = "300", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFF78909C).string(), name = "400", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFF607D8B).string(), name = "500", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFF546E7A).string(), name = "600", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFF455A64).string(), name = "700", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFF37474F).string(), name = "800", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
    ColorInfo(value = Color(0xFF263238).string(), name = "900", palette = Palette.Material(ColorOfMaterial.BLUE_GRAY)),
)

val MaterialPalette = (
    MaterialRed + MaterialPink + MaterialPurple + MaterialDeepPurple + MaterialIndigo + MaterialBlue +
    MaterialLightBlue + MaterialCyan + MaterialTeal + MaterialGreen + MaterialLightGreen + MaterialLime +
    MaterialYellow + MaterialAmber + MaterialOrange + MaterialDeepOrange + MaterialBrown + MaterialGrey +
    MaterialBlue
)


val FlatUIPalette = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFF1ABC9C).string(), name = "Turqoise", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF2ECC71).string(), name = "Emerald", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF3498DB).string(), name = "Peterriver", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF9B59B6).string(), name = "Amethyst", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF34495E).string(), name = "Wetasphalt", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF16A085).string(), name = "Greensea", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF27AE60).string(), name = "Nephritis", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF2980B9).string(), name = "Belizehole", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF8E44AD).string(), name = "Wisteria", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF2C3E50).string(), name = "Midnightblue", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFF1C40F).string(), name = "Sunflower", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFE67E22).string(), name = "Carrot", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFE74C3C).string(), name = "Alizarin", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFECF0F1).string(), name = "Clouds", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF95A5A6).string(), name = "Concrete", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFF39C12).string(), name = "Orange", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFD35400).string(), name = "Pumkin", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFC0392B).string(), name = "Pomegranate", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFFBDC3C7).string(), name = "Silver", palette = Palette.FlatUI),
    ColorInfo(value = Color(0xFF7F8C8D).string(), name = "Asbestos", palette = Palette.FlatUI)
)

val SocialPalette = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFF1877F2).string(), name = "Facebook", palette = Palette.Social),
    ColorInfo(value = Color(0xFF0099FF).string(), name = "Messenger", palette = Palette.Social),
    ColorInfo(value = Color(0xFF1DA1F2).string(), name = "Twitter", palette = Palette.Social),
    ColorInfo(value = Color(0xFF0A66C2).string(), name = "LinkedIn", palette = Palette.Social),
    ColorInfo(value = Color(0xFF00AFF0).string(), name = "Skype", palette = Palette.Social),
    ColorInfo(value = Color(0xFF0061FF).string(), name = "Dropbox", palette = Palette.Social),
    ColorInfo(value = Color(0xFF21759B).string(), name = "Wordpress", palette = Palette.Social),
    ColorInfo(value = Color(0xFF1AB7EA).string(), name = "Vimeo", palette = Palette.Social),
    ColorInfo(value = Color(0xFF0077B5).string(), name = "SlideShare", palette = Palette.Social),
    ColorInfo(value = Color(0xFF4C75A3).string(), name = "VK", palette = Palette.Social),
    ColorInfo(value = Color(0xFF34465D).string(), name = "Tumblr", palette = Palette.Social),
    ColorInfo(value = Color(0xFF410093).string(), name = "Yahoo", palette = Palette.Social),
    ColorInfo(value = Color(0xFFBD081C).string(), name = "Pinterest", palette = Palette.Social),
    ColorInfo(value = Color(0xFFCD201F).string(), name = "Youtube", palette = Palette.Social),
    ColorInfo(value = Color(0xFFFF5700).string(), name = "Reddit", palette = Palette.Social),
    ColorInfo(value = Color(0xFFB92B27).string(), name = "Quora", palette = Palette.Social),
    ColorInfo(value = Color(0xFFAF0606).string(), name = "Yelp", palette = Palette.Social),
    ColorInfo(value = Color(0xFFDF2029).string(), name = "Weibo", palette = Palette.Social),
    ColorInfo(value = Color(0xFFDA552F).string(), name = "ProductHunt", palette = Palette.Social),
    ColorInfo(value = Color(0xFFFF6600).string(), name = "HackerNews", palette = Palette.Social),
    ColorInfo(value = Color(0xFFFF3300).string(), name = "Soundcloud", palette = Palette.Social),
    ColorInfo(value = Color(0xFFF57D00).string(), name = "Blogger", palette = Palette.Social),
    ColorInfo(value = Color(0xFFFFFC00).string(), name = "SnapChat", palette = Palette.Social),
    ColorInfo(value = Color(0xFF25D366).string(), name = "WhatsApp", palette = Palette.Social),
    ColorInfo(value = Color(0xFF25D366).string(), name = "WeChat", palette = Palette.Social),
    ColorInfo(value = Color(0xFF00C300).string(), name = "Line", palette = Palette.Social),
    ColorInfo(value = Color(0xFF02B875).string(), name = "Medium", palette = Palette.Social),
    ColorInfo(value = Color(0xFF00B489).string(), name = "Vine", palette = Palette.Social),
    ColorInfo(value = Color(0xFF3AAF85).string(), name = "Slack", palette = Palette.Social),
    ColorInfo(value = Color(0xFFE4405F).string(), name = "Instagram", palette = Palette.Social),
    ColorInfo(value = Color(0xFFEA4C89).string(), name = "Dribbble", palette = Palette.Social),
    ColorInfo(value = Color(0xFFFF0084).string(), name = "Flickr", palette = Palette.Social),
    ColorInfo(value = Color(0xFFF94877).string(), name = "FourSquare", palette = Palette.Social),
    ColorInfo(value = Color(0xFFEE1D51).string(), name = "TikTok", palette = Palette.Social),
    ColorInfo(value = Color(0xFF131418).string(), name = "Behance", palette = Palette.Social)
)

val MetroPalette = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFA4C400).string(), name = "Lime", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF60A917).string(), name = "Green", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF008A00).string(), name = "Emerald", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF00ABA9).string(), name = "Teal", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF1BA1E2).string(), name = "Cyan", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF0050EF).string(), name = "Cobalt", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF6A00FF).string(), name = "Indigo", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFAA00FF).string(), name = "Violet", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFF472D0).string(), name = "Pink", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFD80073).string(), name = "Magenta", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFA20025).string(), name = "Crimson", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFE51400).string(), name = "Red", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFFA6800).string(), name = "Orange", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFF0A30A).string(), name = "Amber", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFE3C800).string(), name = "Yellow", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF825A2C).string(), name = "Brown", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF6D8764).string(), name = "Olive", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF647687).string(), name = "Steel", palette = Palette.Metro),
    ColorInfo(value = Color(0xFF76608A).string(), name = "Mauve", palette = Palette.Metro),
    ColorInfo(value = Color(0xFFA0522D).string(), name = "Sienna", palette = Palette.Metro)
)

//Kill Me
val HTMLPalette = listOf<ColorInfo>(
    ColorInfo(value = Color(0xFFFFFFFF).string(), name = "white", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF5F5F5).string(), name = "whitesmoke", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFDCDCDC).string(), name = "gainsboro", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFD3D3D3).string(), name = "lightgrey", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFC0C0C0).string(), name = "silver", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFA9A9A9).string(), name = "darkgrey", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF808080).string(), name = "grey", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF696969).string(), name = "dimgrey", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF000000).string(), name = "black", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFE4E1).string(), name = "mistyrose", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF08080).string(), name = "lightcoral", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFA8072).string(), name = "salmon", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFBC8F8F).string(), name = "rosybrown", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF6347).string(), name = "tomato", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFCD5C5C).string(), name = "indianred", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF0000).string(), name = "red", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFB22222).string(), name = "firebrick", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFA52A2A).string(), name = "brown", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF8B0000).string(), name = "darked", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF800000).string(), name = "maroon", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFF5EE).string(), name = "seashell", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFDAB9).string(), name = "peachpuff", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFA07A).string(), name = "lightsalmon", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFE9967A).string(), name = "darksalmon", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF4A460).string(), name = "sandybrown", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF7F50).string(), name = "coral", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFCD853F).string(), name = "peru", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF4500).string(), name = "orangered", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFD2691E).string(), name = "chocolate", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFA0522D).string(), name = "sienna", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF8B4513).string(), name = "saddleborwn", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFFAFA).string(), name = "snow", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFFAF0).string(), name = "floralwhite", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFDF5E6).string(), name = "oldlace", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFAF0E6).string(), name = "linen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFEFD5).string(), name = "papayawhip", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFAEBD7).string(), name = "antiquewhite", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFEBCD).string(), name = "blanchedalmond", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFE4C4).string(), name = "bisque", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFE4B5).string(), name = "moccasin", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFDEAD).string(), name = "navajowhite", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF5DEB3).string(), name = "wheat", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFDEB887).string(), name = "burlywood", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFD2B48C).string(), name = "tan", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF8C00).string(), name = "darkorange", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFA500).string(), name = "orange", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFDAA520).string(), name = "goldenrod", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFB8860B).string(), name = "darkgoldenrod", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFF8DC).string(), name = "cornsilk", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFFACD).string(), name = "lemonchiffon", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFEEE8AA).string(), name = "palegoldenrod", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF0E68C).string(), name = "khaki", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFBDB76B).string(), name = "dakrkhaki", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFD700).string(), name = "gold", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFFFF0).string(), name = "ivory", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFFFE0).string(), name = "lightyellow", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF5F5DC).string(), name = "beige", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFAFAD2).string(), name = "lightgoldenrodyellow", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFFF00).string(), name = "yellow", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF808000).string(), name = "olive", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFADFF2F).string(), name = "greenyellow", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF9ACD32).string(), name = "yellowgreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF6B8E23).string(), name = "olivedrab", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF556B2F).string(), name = "darkolivegreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF7FFF00).string(), name = "chartreuse", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF7CFC00).string(), name = "lawngreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF0FFF0).string(), name = "honeydew", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF98FB98).string(), name = "palegreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF90EE90).string(), name = "lightgreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF8FBC8F).string(), name = "darkseagreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF00FF00).string(), name = "lime", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF32CD32).string(), name = "limegreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF228B22).string(), name = "forestgreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF008000).string(), name = "green", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF006400).string(), name = "darkgreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF00FF7F).string(), name = "springgreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF3CB371).string(), name = "mediumseagreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF2E8B57).string(), name = "seagreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF5FFFA).string(), name = "mintcream", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF7FFFD4).string(), name = "aquamarine", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF66CDAA).string(), name = "mediumaquamarine", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF00FA9A).string(), name = "mediumspringgreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF40E0D0).string(), name = "turquoise", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF48D1CC).string(), name = "mediumturquoise", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF20B2AA).string(), name = "lightseagreen", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF0FFFF).string(), name = "azure", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFE0FFFF).string(), name = "lightcyan", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFAFEEEE).string(), name = "paleterquoise", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFB0E0E6).string(), name = "powderblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFADD8E6).string(), name = "lightblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF00FFFF).string(), name = "cyan", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF5F9EA0).string(), name = "cadetblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF00CED1).string(), name = "darkurquoise", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF008B8B).string(), name = "darkcyan", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF008080).string(), name = "teal", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF2F4F4F).string(), name = "darkslategrey", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF0F8FF).string(), name = "aliceblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF87CEFA).string(), name = "lightskyblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF87CEEB).string(), name = "skyblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF1E90FF).string(), name = "dodgerblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF00BFFF).string(), name = "deepskyblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF4682B4).string(), name = "steelblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFB0C4DE).string(), name = "lightsteelblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF6495ED).string(), name = "cornflowerblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF4169E1).string(), name = "royalblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF778899).string(), name = "lightslategrey", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF708090).string(), name = "slategrey", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFE6E6FA).string(), name = "lavender", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF7B68EE).string(), name = "mediumslateblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF6A5ACD).string(), name = "stateblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF0000FF).string(), name = "blue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF0000CD).string(), name = "mediumblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF483D8B).string(), name = "darkslateblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF00008B).string(), name = "darkblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF191970).string(), name = "midnightblue", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF000080).string(), name = "navy", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF9370DB).string(), name = "mediumpurple", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFF8F8FF).string(), name = "ghostwhite", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF8A2BE2).string(), name = "blueviolet", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF9932CC).string(), name = "darkorchid", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF9400D3).string(), name = "darkviolet", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF4B0082).string(), name = "indigo", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFBA55D3).string(), name = "mediumorchid", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFD8BFD8).string(), name = "thistle", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFDDA0DD).string(), name = "plum", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFEE82EE).string(), name = "violet", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFDA70D6).string(), name = "orchid", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF00FF).string(), name = "magenta", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF8B008B).string(), name = "darkmagenta", palette = Palette.HTML),
    ColorInfo(value = Color(0xFF800080).string(), name = "purple", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF1493).string(), name = "deeppink", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFC71585).string(), name = "mediumvioletred", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFC0CB).string(), name = "pink", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFB6C1).string(), name = "lightpink", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFDC143C).string(), name = "crimson", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFFF0F5).string(), name = "lavenderblush", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFFF69B4).string(), name = "hotpink", palette = Palette.HTML),
    ColorInfo(value = Color(0xFFDB7093).string(), name = "palevioletred", palette = Palette.HTML)
)







