package com.honyadew.harmony.view

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.honey.harmony.R
import com.honyadew.harmony.contract.HarmonyMode
import com.honyadew.harmony.contract.HarmonyState
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.extencion.string
import com.honyadew.extencion.toHexString
import com.honyadew.extencion.toStringRGB
import com.honyadew.extencion.toStringRGBA
import com.honyadew.harmony.extension.getFullHarmony
import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorSchemeSource
import com.honyadew.model.CustomColorScheme
import com.honyadew.harmony_color_picker.HarmonyColorPicker
import com.honyadew.harmony_color_picker.model.ColorPickerDefaults
import com.honyadew.harmony_color_picker.model.HsvColor
import com.honyadew.harmony_color_picker.model.SliderPosition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HarmonyViewShow(
    state: HarmonyState.Show,
    onColorValueChanged: (newValue: Color, harmony: HarmonyMode) -> Unit,
    onSwapCopyMode: () -> Unit,
    onSave: (ColorScheme: com.honyadew.model.CustomColorScheme) -> Unit,
    pagerState: PagerState = rememberPagerState()
) {
    val portraitMode: Boolean =
        (LocalContext.current as Activity)
            .resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    Column {
        HarmonyTabRow(pagerState = pagerState)

        if (portraitMode) {
            PortraitHarmonyViewShow(
                state = state,
                onColorValueChanged = onColorValueChanged,
                pagerState = pagerState,
                onSwapCopyMode = onSwapCopyMode,
                onSave = onSave
            )
        } else {
            LandscapeHarmonyViewShow(
                state = state,
                onColorValueChanged = onColorValueChanged,
                pagerState = pagerState,
                onSwapCopyMode = onSwapCopyMode,
                onSave = onSave
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PortraitHarmonyViewShow(
    state: HarmonyState.Show,
    onColorValueChanged: (newValue: Color, harmony: HarmonyMode) -> Unit,
    pagerState: PagerState,
    onSwapCopyMode: () -> Unit,
    onSave: (ColorScheme: com.honyadew.model.CustomColorScheme) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    HorizontalPager(
        modifier = modifier,
        pageCount = HarmonyMode.values().size,
        state = pagerState,
    ) { page ->
        val colors: List<Color> = state.harmoniesValue[page].getFullHarmony(HarmonyMode.values()[page])

        Column {
            ColorsDisplay(
                colors = colors,
                modifier = Modifier
                    .weight(0.25f)
                    .padding(top = 4.dp, start = 4.dp, end = 4.dp),
                roundedCornerShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 4.dp)
                    .weight(0.75f)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = colorSelect(90)),
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    ColorsText(
                        colors = colors,
                        HexAsCopyType = state.hexAsCopyMode
                    )
                    SwapNameSaveRow(
                        onSwap = { onSwapCopyMode.invoke() },
                        onSave = { name ->
                            onSave.invoke(
                                CustomColorScheme(
                                    colors = colors.map {
                                        ColorInfo(
                                            value = it.string(),
                                            name = ""
                                        )
                                    },
                                    name = name,
                                    source = ColorSchemeSource.FromHarmony
                                )
                            )
                        },
                        initialName = stringResource(id = HarmonyMode.values()[page].nameResId)
                    )
                }
                HarmonyColorPicker(
                    harmonyMode = HarmonyMode.values()[page].colorHarmonyMode,
                    value = HsvColor.from(state.harmoniesValue[page]),
                    onValueChanged = { newValue ->
                        Log.d("MyLog", "NewValue -> $newValue")
                        onColorValueChanged.invoke(newValue.toColor(), HarmonyMode.values()[page])
                    },
                    brightnessBarPosition = SliderPosition.BOTTOM,
                    alphaBarPosition = SliderPosition.BOTTOM,
                    colors = ColorPickerDefaults.colors(
                        brightnessBarColor = colorSelect(saturation = 70, darkTheme = true),
                        alphaBarColor = colorSelect(saturation = 70, inverse = true, darkTheme = true)
                    ),
                    paddings = ColorPickerDefaults.paddings(allPaddingValues = PaddingValues(
                      end = 16.dp, start = 16.dp, top = 4.dp
                    )),
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LandscapeHarmonyViewShow(
    state: HarmonyState.Show,
    onColorValueChanged: (newValue: Color, harmony: HarmonyMode) -> Unit,
    pagerState: PagerState,
    onSwapCopyMode: () -> Unit,
    onSave: (ColorScheme: com.honyadew.model.CustomColorScheme) -> Unit,
    modifier: Modifier = Modifier
) {
    VerticalPager(
        modifier = modifier,
        pageCount = HarmonyMode.values().size,
        state = pagerState,
    ) { page ->
        val colors: List<Color> = state.harmoniesValue[page].getFullHarmony(HarmonyMode.values()[page])

        Row(modifier = modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .weight(0.5f)
                .padding(top = 4.dp)) {
                ColorsDisplay(
                    colors = colors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.45f),
                    roundedCornerShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                Card(
                    modifier = modifier,
                    colors = CardDefaults.cardColors(containerColor = colorSelect(90)),
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    ColorsText(
                        colors = colors,
                        HexAsCopyType = state.hexAsCopyMode
                    )
                    SwapNameSaveRow(
                        onSwap = { onSwapCopyMode.invoke() },
                        onSave = { name ->
                            onSave.invoke(
                                CustomColorScheme(
                                    colors = colors.map {
                                        ColorInfo(
                                            value = it.string(),
                                            name = ""
                                        )
                                    },
                                    name = name,
                                    source = ColorSchemeSource.FromHarmony
                                )
                            )
                        },
                        initialName = stringResource(id = HarmonyMode.values()[page].nameResId)
                    )
                }
            }
            HarmonyColorPicker(
                harmonyMode = HarmonyMode.values()[page].colorHarmonyMode,
                value = HsvColor.from(state.harmoniesValue[page]),
                onValueChanged = { newValue ->
                    onColorValueChanged.invoke(newValue.toColor(), HarmonyMode.values()[page])
                },
                brightnessBarPosition = SliderPosition.END,
                alphaBarPosition = SliderPosition.END,
                colors = ColorPickerDefaults.colors(
                    brightnessBarColor = colorSelect(saturation = 70, darkTheme = true),
                    alphaBarColor = colorSelect(saturation = 70, inverse = true, darkTheme = true)
                ),
                paddings = ColorPickerDefaults.paddings(allPaddingValues = PaddingValues(
                    end = 16.dp, top = 4.dp, bottom = 8.dp, start = 16.dp
                )),
                modifier = Modifier.weight(0.5f)
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HarmonyTabRow(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    coroutine: CoroutineScope = rememberCoroutineScope()
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        containerColor = colorSelect(),
        contentColor = colorSelect(saturation = 90, inverse = true),
        indicator = {tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(colorSelect(saturation = 90, inverse = true))
            )
        }
    ) {
        HarmonyMode.values().forEach { harmonyMode ->
            Tab(
                selected = harmonyMode == HarmonyMode.values()[pagerState.currentPage],
                onClick = {
                    coroutine.launch {
                        pagerState.scrollToPage(HarmonyMode.values().indexOf(harmonyMode))
                    }
                },
                icon = {
                    val iconResId = if(harmonyMode == HarmonyMode.values()[pagerState.currentPage]){
                        harmonyMode.iconResId
                    } else {
                        harmonyMode.outlinedIconResId
                    }
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = stringResource(id = harmonyMode.nameResId)
                    )
                }
            )
        }
    }
}

@Composable
private fun ColorsDisplay(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(16.dp),
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colorSelect(90)),
        shape = roundedCornerShape,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        OutlinedCard(
            modifier = Modifier,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = 2.dp, color = colorSelect()),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(color)
                            .fillMaxHeight()
                    )
                }
            }
        }
    }
}

@Composable
private fun ColorsText(
    colors: List<Color>,
    HexAsCopyType: Boolean,
    modifier: Modifier = Modifier,
    clipboardManager: ClipboardManager = LocalClipboardManager.current
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        colors.forEach { color ->
            Column(
                modifier = Modifier
                    .clickable {
                        clipboardManager.setText(
                            AnnotatedString(buildString {
                                append(
                                    if (HexAsCopyType) {
                                        color.toHexString()
                                    } else {
                                        color.toStringRGBA()
                                    }
                                )
                            })
                        )
                    }
                    .defaultMinSize(minHeight = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (HexAsCopyType) {
                        color.toHexString()
                    } else {
                        color.toStringRGB()
                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Text(
                    text = if (!HexAsCopyType) {
                        color.toHexString()
                    } else {
                        color.toStringRGB()
                    },
                    fontSize = 10.sp
                )
            }
        }
    }
}


@Composable
private fun SwapNameSaveRow(
    onSwap: () -> Unit,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier,
    initialName: String = "",
    nameState: MutableState<String> = remember { mutableStateOf(initialName) },
    showNameField: MutableState<Boolean> = remember{ mutableStateOf(false)},
) {
    Row(
        modifier = modifier.animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val rotate = remember { Animatable(0f) }
        LaunchedEffect(showNameField.value){
            rotate.animateTo(
                targetValue = if (showNameField.value) 90f else 0f
            )
        }
        IconButton(
            modifier = Modifier
                .weight(0.15f)
                .height(36.dp),
            onClick = {
                showNameField.value = !showNameField.value
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_to_end),
                contentDescription = "Arrow",
                modifier = Modifier.graphicsLayer(rotationZ = rotate.value)
            )
        }
        if (showNameField.value){
            OutlinedTextField(
                value = nameState.value,
                onValueChange = { newValue ->
                    if (newValue.length < 21) nameState.value = newValue
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 4.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorSelect(saturation = 70),
                    unfocusedBorderColor = colorSelect(saturation = 80),
                    focusedLabelColor = colorSelect(saturation = 70),
                    cursorColor = colorSelect(saturation = 90, inverse = true)
                ),
                singleLine = true,
                label = {
                    Text(text = stringResource(id = R.string.name_color_scheme))
                }
            )
            IconButton(
                modifier = Modifier
                    .weight(0.15f)
                    .height(36.dp),
                onClick = {
                    onSave.invoke(nameState.value)
                    nameState.value = initialName
                    showNameField.value = false
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_save_24), contentDescription = "Save")
            }
        } else {
            Spacer(modifier = Modifier.weight(0.75f))
            IconButton(
                modifier = Modifier
                    .weight(0.15f)
                    .height(36.dp),
                onClick = { onSwap.invoke() }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_swap_24), contentDescription = "Swap")
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewHarmony() {
    HarmonyViewShow(
        state = HarmonyState.Show(),
        onColorValueChanged = { newValue, harmony -> },
        onSwapCopyMode = {},
        onSave = {}
    )
}