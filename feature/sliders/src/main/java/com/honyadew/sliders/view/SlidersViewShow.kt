package com.honyadew.sliders.view

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honyadew.sliders.R
import com.honyadew.designsystem.view.DcpSlider
import com.honyadew.designsystem.view.RowToSave
import com.honyadew.sliders.contract.SlidersState
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.extencion.string
import com.honyadew.extencion.toHexString
import com.honyadew.extencion.toStringRGBA
import com.honyadew.sliders.model.SlidersType
import kotlin.math.roundToInt


@Composable
fun SlidersViewShow(
    state: SlidersState.Show,
    onFirstSliderChange: (newValue: Float) -> Unit,
    onSecondSliderChange: (newValue: Float) -> Unit,
    onThirdSliderChange: (newValue: Float) -> Unit,
    onAlphaSliderChange: (newValue: Float) -> Unit,
    onChangeSlidersType: (type: SlidersType) -> Unit,
    onAddToSaveList: (color: com.honyadew.model.ColorInfo) -> Unit,
    onSaveColorScheme: (colorScheme: com.honyadew.model.CustomColorScheme) -> Unit,
    onRemoveFromToSaveList : (color : com.honyadew.model.ColorInfo) -> Unit,
) {
    val portraitMode: Boolean =
        (LocalContext.current as Activity)
            .resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val totalColor = getColorBySliders(
        state.type,
        state.sliderOne,
        state.sliderTwo,
        state.sliderThree,
        state.sliderAlpha
    )

    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = colorSelect()),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        if (portraitMode) {
            PortraitSlidersViewShow(
                state = state,
                totalColor = totalColor,
                onFirstSliderChange = onFirstSliderChange,
                onSecondSliderChange = onSecondSliderChange,
                onThirdSliderChange = onThirdSliderChange,
                onAlphaSliderChange = onAlphaSliderChange,
                onChangeSlidersType = onChangeSlidersType,
                onAddToSaveList = onAddToSaveList,
                onSaveColorScheme = onSaveColorScheme,
                onRemoveFromToSaveList = onRemoveFromToSaveList,
                modifier = Modifier.padding(8.dp)
            )
        } else {
            LandscapeSlidersViewShow(
                state = state,
                totalColor = totalColor,
                onFirstSliderChange = onFirstSliderChange,
                onSecondSliderChange = onSecondSliderChange,
                onThirdSliderChange = onThirdSliderChange,
                onAlphaSliderChange = onAlphaSliderChange,
                onChangeSlidersType = onChangeSlidersType,
                onAddToSaveList = onAddToSaveList,
                onSaveColorScheme = onSaveColorScheme,
                onRemoveFromToSaveList = onRemoveFromToSaveList,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun PortraitSlidersViewShow(
    state: SlidersState.Show,
    totalColor: Color,
    onFirstSliderChange: (newValue: Float) -> Unit,
    onSecondSliderChange: (newValue: Float) -> Unit,
    onThirdSliderChange: (newValue: Float) -> Unit,
    onAlphaSliderChange: (newValue: Float) -> Unit,
    onChangeSlidersType: (type: SlidersType) -> Unit,
    onAddToSaveList: (color: com.honyadew.model.ColorInfo) -> Unit,
    onSaveColorScheme: (colorScheme: com.honyadew.model.CustomColorScheme) -> Unit,
    onRemoveFromToSaveList : (color : com.honyadew.model.ColorInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        RowToSave(
            colorsToSave = state.colorsToSave,
            onSaveColorScheme = onSaveColorScheme,
            onRemoveFromToSaveList = onRemoveFromToSaveList
        )
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .defaultMinSize(minHeight = 128.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = 2.dp, color = colorSelect()),
            content = {},
            colors = CardDefaults.cardColors(containerColor = totalColor),
            elevation = CardDefaults.cardElevation(2.dp)
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            CopyAndStatsSliders(state = state, totalColor = totalColor)

            SlidersCell(
                state = state,
                onFirstSliderChange = onFirstSliderChange,
                onSecondSliderChange = onSecondSliderChange,
                onThirdSliderChange = onThirdSliderChange,
                onAlphaSliderChange = onAlphaSliderChange,
                onChangeSlidersType = onChangeSlidersType,
                onAddToSaveList = onAddToSaveList,
            )
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
private fun LandscapeSlidersViewShow(
    state: SlidersState.Show,
    totalColor: Color,
    onFirstSliderChange: (newValue: Float) -> Unit,
    onSecondSliderChange: (newValue: Float) -> Unit,
    onThirdSliderChange: (newValue: Float) -> Unit,
    onAlphaSliderChange: (newValue: Float) -> Unit,
    onChangeSlidersType: (type: SlidersType) -> Unit,
    onAddToSaveList: (color: com.honyadew.model.ColorInfo) -> Unit,
    onSaveColorScheme: (colorScheme: com.honyadew.model.CustomColorScheme) -> Unit,
    onRemoveFromToSaveList : (color : com.honyadew.model.ColorInfo) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(0.5f)
            ) {
            RowToSave(
                colorsToSave = state.colorsToSave,
                onSaveColorScheme = onSaveColorScheme,
                onRemoveFromToSaveList = onRemoveFromToSaveList
            )
            OutlinedCard(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 2.dp, color = colorSelect()),
                content = {},
                colors = CardDefaults.cardColors(containerColor = totalColor),
                elevation = CardDefaults.cardElevation(2.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CopyAndStatsSliders(state = state, totalColor = totalColor)

            Column {
                SlidersCell(
                    state = state,
                    onFirstSliderChange = onFirstSliderChange,
                    onSecondSliderChange = onSecondSliderChange,
                    onThirdSliderChange = onThirdSliderChange,
                    onAlphaSliderChange = onAlphaSliderChange,
                    onChangeSlidersType = onChangeSlidersType,
                    onAddToSaveList = onAddToSaveList
                )
                Spacer(modifier = Modifier.size(32.dp))
            }

        }
    }

}

@Composable
private fun CopyAndStatsSliders(
    state: SlidersState.Show,
    totalColor: Color,
    clipboardManager: ClipboardManager = LocalClipboardManager.current
) {
    Column {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val rgbaOrHsv = when (state.type) {
                SlidersType.RGB -> {totalColor.toStringRGBA()}

                SlidersType.HSV -> {
                    "${(state.sliderOne * 360).roundToInt()}," +
                            "${(state.sliderTwo * 100).roundToInt()}," +
                            "${(state.sliderThree * 100).roundToInt()}," +
                            "${(state.sliderAlpha * 100).roundToInt()}"
                }
            }
            val hex = totalColor.toHexString()

            Button(
                onClick = { clipboardManager.setText(AnnotatedString(buildString { append(rgbaOrHsv) })) },
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90)),
                modifier = Modifier
                    .weight(0.6f)
                    .widthIn(max = (256.dp))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = if (state.type == SlidersType.RGB) "RGBA" else "HSV")
                    Text(text = rgbaOrHsv)
                }
            }
            Spacer(modifier = Modifier.weight(0.05f))
            Button(
                onClick = { clipboardManager.setText(AnnotatedString(buildString { append(hex) })) },
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90)),
                modifier = Modifier
                    .weight(0.6f)
                    .widthIn(max = (256.dp))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "HEX")
                    Text(text = hex)
                }
            }
        }
    }
}

@Composable
private fun SlidersCell(
    state: SlidersState.Show,
    onFirstSliderChange: (newValue: Float) -> Unit,
    onSecondSliderChange: (newValue: Float) -> Unit,
    onThirdSliderChange: (newValue: Float) -> Unit,
    onAlphaSliderChange: (newValue: Float) -> Unit,
    onChangeSlidersType: (type: SlidersType) -> Unit,
    onAddToSaveList: (color: com.honyadew.model.ColorInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90)),
                modifier = Modifier.padding(start = 8.dp),
                onClick = { onAddToSaveList.invoke(
                    com.honyadew.model.ColorInfo(
                        value = getColorBySliders(
                            state.type,
                            state.sliderOne,
                            state.sliderTwo,
                            state.sliderThree,
                            state.sliderAlpha
                        ).string(),
                        name = ""
                    )
                ) }
            ) {
                Text(text = stringResource(id = R.string.to_save))
            }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90)),
                modifier = Modifier
                    .padding(end = 8.dp),
                onClick = {
                    onChangeSlidersType.invoke(if (state.type == SlidersType.RGB) SlidersType.HSV else SlidersType.RGB)
                }) {
                Text(text = if (state.type == SlidersType.RGB) "HSV" else "RGB")
            }
        }
        DcpSlider(
            value = state.sliderOne,
            onValueChange = onFirstSliderChange,
            steps = state.type.assetOfFirst.steps,
            color = state.type.assetOfFirst.color ?: colorSelect(inverse = true),
            leadingName = state.type.assetOfFirst.name
        )
        DcpSlider(
            value = state.sliderTwo,
            onValueChange = onSecondSliderChange,
            steps = state.type.assetOfSecond.steps,
            color = state.type.assetOfSecond.color ?: colorSelect(inverse = true),
            leadingName = state.type.assetOfSecond.name
        )
        DcpSlider(
            value = state.sliderThree,
            onValueChange = onThirdSliderChange,
            steps = state.type.assetOfThird.steps,
            color = state.type.assetOfThird.color ?: colorSelect(inverse = true),
            leadingName = state.type.assetOfThird.name
        )
        DcpSlider(
            value = state.sliderAlpha,
            onValueChange = onAlphaSliderChange,
            steps = state.type.assetOfAlpha.steps,
            color = state.type.assetOfAlpha.color ?: colorSelect(inverse = true),
            leadingName = state.type.assetOfAlpha.name
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)


private fun getColorBySliders(
    slidersType: SlidersType,
    firstValue: Float,
    secondValue: Float,
    thirdValue: Float,
    alphaValue: Float,
): Color {
    Log.d("MyLog", "redrawing")
    return when (slidersType) {
        SlidersType.RGB -> {
            Color(
                red = firstValue,
                green = secondValue,
                blue = thirdValue,
                alpha = alphaValue
            )
        }

        SlidersType.HSV -> {
            val hue = firstValue * 360 // Convert hue value to degrees (0-360)
            val saturation = secondValue
            val value = thirdValue

            val rgbColor = android.graphics.Color.HSVToColor(
                floatArrayOf(hue, saturation, value)
            )

            val red = android.graphics.Color.red(rgbColor) / 255f
            val green = android.graphics.Color.green(rgbColor) / 255f
            val blue = android.graphics.Color.blue(rgbColor) / 255f

            Color(
                red = red,
                green = green,
                blue = blue,
                alpha = alphaValue
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSliderViewShow() {

    SlidersViewShow(
        state = SlidersState.Show(
            type = SlidersType.RGB,
            colorsToSave = listOf(
                com.honyadew.model.ColorInfo(value = Color.Green.string(), name = ""),
                com.honyadew.model.ColorInfo(value = Color.Yellow.string(), name = ""),
                com.honyadew.model.ColorInfo(value = Color.Red.string(), name = ""),
                com.honyadew.model.ColorInfo(value = Color.Transparent.string(), name = ""),
                com.honyadew.model.ColorInfo(value = Color.Black.string(), name = ""),
            )
        ),
        onAlphaSliderChange = {},
        onFirstSliderChange = {},
        onSecondSliderChange = {},
        onThirdSliderChange = {},
        onChangeSlidersType = {},
        onAddToSaveList = {},
        onSaveColorScheme = {},
        onRemoveFromToSaveList = {}
    )

}
