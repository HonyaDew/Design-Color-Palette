package com.honey.designcolorpalette.ui.screen.image.view

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.extencion.color
import com.honey.designcolorpalette.extencion.string
import com.honey.designcolorpalette.extencion.toHexString
import com.honey.designcolorpalette.extencion.toStringRGBA
import com.honey.designcolorpalette.ui.main.view.DcpSlider
import com.honey.designcolorpalette.ui.main.view.RowToSave
import com.honey.designcolorpalette.ui.screen.image.contract.ImageState
import com.honey.designcolorpalette.ui.theme.colorSelect
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorSchemeSource
import com.honey.domain.model.CustomColorScheme

@Composable
fun ImageViewShow(
    state: ImageState.Show,
    onSaveColorScheme: (colorScheme : CustomColorScheme) -> Unit,
    onRemoveFromToSave: (colorInfo: ColorInfo) -> Unit,
    onMoveToSave: (colorInfo: ColorInfo) -> Unit,
    onChangeSelectedColor: (color: Color) -> Unit,
) {
    val portraitMode: Boolean =
        (LocalContext.current as Activity)
            .resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    val showDropDownMenu = remember{ mutableStateOf(false)}

    Box(modifier = Modifier.fillMaxSize()){

        if (portraitMode) {
            PortraitImageViewShow(
                state = state,
                onSaveColorScheme = onSaveColorScheme,
                onRemoveFromToSave = onRemoveFromToSave,
                onMoveToSave = onMoveToSave,
                onChangeSelectedColor = onChangeSelectedColor,
                onOpenDropDownMenu = {showDropDownMenu.value = it},
                showDropDownMenu = showDropDownMenu.value
            )
        } else{
            LandscapeImageViewShow(
                state = state,
                onSaveColorScheme = onSaveColorScheme,
                onRemoveFromToSave = onRemoveFromToSave,
                onMoveToSave = onMoveToSave,
                onChangeSelectedColor = onChangeSelectedColor,
                onOpenDropDownMenu = {showDropDownMenu.value = it},
                showDropDownMenu = showDropDownMenu.value
            )
        }
        if (showDropDownMenu.value){
            DropDownMenu(modifier = Modifier.padding(top = 64.dp))
        }
    }
}

@Composable
fun PortraitImageViewShow(
    state: ImageState.Show,
    showDropDownMenu : Boolean,
    onSaveColorScheme: (colorScheme : CustomColorScheme) -> Unit,
    onRemoveFromToSave: (colorInfo: ColorInfo) -> Unit,
    onMoveToSave: (colorInfo: ColorInfo) -> Unit,
    onChangeSelectedColor: (color: Color) -> Unit,
    onOpenDropDownMenu : (open: Boolean) -> Unit,
    alphaValueState: MutableState<Float> = remember{ mutableStateOf(1f)}
) {
    Column {
        TabOfDropDownMenu(
            showDropDownMenu = showDropDownMenu,
            extractedColors = state.extractedColors,
            onSaveColorScheme = onSaveColorScheme,
            onOpenDropDownMenu = onOpenDropDownMenu
        )
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = colorSelect()),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (state.colorsToSave.isNotEmpty()){
                    RowToSave(
                        colorsToSave = state.colorsToSave,
                        onSaveColorScheme = onSaveColorScheme,
                        onRemoveFromToSaveList = onRemoveFromToSave
                    )
                }
                //TODO image implement
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(vertical = 4.dp)
                    .background(Color.Green)
                    .clickable {
                        onChangeSelectedColor.invoke(
                            Color(red = 0f, green = 1f, blue = 0f, alpha = alphaValueState.value)
                        )
                    }
                )
                FunctionalRow(
                    modifier = Modifier.fillMaxHeight(),
                    selectedColor = state.selectedColor,
                    alphaValueState = alphaValueState,
                    onMoveToSave = onMoveToSave
                )
            }
        }
    }
}


@Composable
fun LandscapeImageViewShow(
    state: ImageState.Show,
    showDropDownMenu: Boolean,
    onSaveColorScheme: (colorScheme: CustomColorScheme) -> Unit,
    onRemoveFromToSave: (colorInfo: ColorInfo) -> Unit,
    onMoveToSave: (colorInfo: ColorInfo) -> Unit,
    onChangeSelectedColor: (color: Color) -> Unit,
    onOpenDropDownMenu: (open: Boolean) -> Unit,
    alphaValueState: MutableState<Float> = remember{ mutableStateOf(1f)}
) {
    Row {
        Box(modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 24.dp, top = 4.dp)
            .fillMaxHeight()
            .aspectRatio(1f)
            .background(Color.Green)
            .clickable {
                onChangeSelectedColor.invoke(
                    Color(red = 0f, green = 1f, blue = 0f, alpha = alphaValueState.value)
                )
            }
        )
        Column {
            TabOfDropDownMenu(
                showDropDownMenu = showDropDownMenu,
                extractedColors = state.extractedColors,
                onSaveColorScheme = onSaveColorScheme,
                onOpenDropDownMenu = onOpenDropDownMenu
            )
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = colorSelect()),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    if (state.colorsToSave.isNotEmpty()){
                        RowToSave(
                            colorsToSave = state.colorsToSave,
                            onSaveColorScheme = onSaveColorScheme,
                            onRemoveFromToSaveList = onRemoveFromToSave
                        )
                    }

                    FunctionalRow(
                        modifier = Modifier.fillMaxHeight(),
                        selectedColor = state.selectedColor,
                        alphaValueState = alphaValueState,
                        onMoveToSave = onMoveToSave
                    )
                }
            }
        }
    }

}

//TODO maybe should add a Icons for buttons
@Composable
private fun FunctionalRow(
    selectedColor: Color,
    alphaValueState: MutableState<Float>,
    onMoveToSave: (colorInfo: ColorInfo) -> Unit,
    modifier: Modifier = Modifier,
    clipboardManager: ClipboardManager = LocalClipboardManager.current
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = { clipboardManager.setText(AnnotatedString(buildString {
                    append(selectedColor.copy(alpha = alphaValueState.value)
                        .toStringRGBA())
                })) },
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90)),
                modifier = Modifier
                    .weight(0.6f)
                    .widthIn(max = (256.dp))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "RGBA")
                    Text(text = selectedColor.copy(alpha = alphaValueState.value)
                        .toStringRGBA(), maxLines = 1)
                }
            }
            Spacer(modifier = Modifier.weight(0.05f))
            Button(
                onClick = { clipboardManager.setText(AnnotatedString(buildString {
                    append(selectedColor.copy(alpha = alphaValueState.value)
                        .toHexString())
                })) },
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90)),
                modifier = Modifier
                    .weight(0.6f)
                    .widthIn(max = (256.dp))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "HEX")
                    Text(text = selectedColor.copy(alpha = alphaValueState.value)
                        .toHexString(), maxLines = 1)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(0.3f),
                onClick = {
                    onMoveToSave.invoke(ColorInfo(
                        value = selectedColor.copy(alpha = alphaValueState.value).string(),
                        name = ""
                    ))
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90))
            ) {
                Text(text = "ToSave")
            }
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(horizontal = 4.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 2.dp, color = colorSelect()),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
                    .background(selectedColor.copy(alpha = alphaValueState.value))
                )
            }
            Button(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(0.3f),
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(containerColor = colorSelect(saturation = 90))
            ) {
                Text(text = "New Image")
            }
        }
        DcpSlider(
            value = alphaValueState.value,
            onValueChange = { newValue ->
                alphaValueState.value = newValue
            },
            steps = 100
        )
    }
}

@Composable
fun TabOfDropDownMenu(
    showDropDownMenu : Boolean,
    extractedColors: List<ColorInfo>,
    onSaveColorScheme: (colorScheme: CustomColorScheme) -> Unit,
    onOpenDropDownMenu : (open: Boolean) -> Unit,
    modifier : Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = colorSelect(saturation = 90))
    ) {
        Row {
            val arrowIconResId = if (showDropDownMenu)R.drawable.ic_arrow_to_bottom else R.drawable.ic_arrow_to_end
            IconButton(
                modifier = Modifier.weight(0.1f),
                onClick = {
                    onOpenDropDownMenu.invoke(!showDropDownMenu)
                }
            ) {
                Icon(painter = painterResource(id = arrowIconResId), contentDescription = "Arrow")
            }
            OutlinedCard(
                modifier = Modifier.weight(0.6f),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 2.dp, color = colorSelect()),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    extractedColors.forEach { colorInfo ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(colorInfo.value.color())
                                .height(48.dp)
                        )
                    }
                }
            }
            val basicName = stringResource(id = R.string.extracted_colors)
            IconButton(
                modifier = Modifier.weight(0.1f),
                onClick = {
                    onSaveColorScheme.invoke(CustomColorScheme(
                        colors = extractedColors,
                        name = basicName,
                        source = ColorSchemeSource.ExtractAuto
                    ))
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_save_24), contentDescription = "Save")
            }
        }
    }
}

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 256.dp)
        .background(Color.Yellow)){

    }
}