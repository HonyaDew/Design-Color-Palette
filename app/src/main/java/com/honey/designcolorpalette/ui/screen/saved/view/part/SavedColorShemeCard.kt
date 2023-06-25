package com.honey.designcolorpalette.ui.screen.saved.view.part

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.extencion.toHexString
import com.honey.designcolorpalette.ui.theme.colorSelect
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.CustomColorScheme
import com.honey.designcolorpalette.extencion.color
import com.honey.palette.extencion.string

@Composable
fun SavedColorSchemeCard(
    paletteInfo: CustomColorScheme,
    onPaletteClick: (palette: CustomColorScheme) -> Unit,
    onDeletePalette: (palette: CustomColorScheme) -> Unit,
    modifier: Modifier = Modifier,
    clipboardManager: ClipboardManager = LocalClipboardManager.current
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colorSelect(90)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPaletteClick.invoke(paletteInfo) },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = 2.dp, color = colorSelect()),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        //TODO try remove a 2.dp
                        .defaultMinSize(minHeight = 128.dp)
                ) {
                    paletteInfo.colors.forEach { colorInfo ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(colorInfo.value.color())
                                .height(128.dp)
                        )
                    }
                }
                FilledIconButton(
                    onClick = { onDeletePalette.invoke(paletteInfo) },
                    modifier = Modifier
                        .padding(top = 6.dp, end = 6.dp)
                        .size(24.dp)
                        .align(Alignment.TopEnd),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = colorSelect(),
                        contentColor = colorSelect(saturation = 90,inverse = true)
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close_24),
                        contentDescription = "Delete"
                    )
                }
            }
        }

        when (paletteInfo.colors.size) {
            in 1..4 -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .defaultMinSize(minHeight = 48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    paletteInfo.colors.forEach { colorInfo ->
                        Column(
                            modifier = Modifier
                                .clickable {
                                    clipboardManager.setText(
                                        AnnotatedString(buildString {
                                            append(
                                                colorInfo.value
                                                    .color()
                                                    .toHexString()
                                            )
                                        })
                                    )
                                }
                                .defaultMinSize(minHeight = 48.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "HEX", fontWeight = FontWeight.SemiBold)
                            Text(
                                text = colorInfo.value.color().toHexString(),
                            )
                        }
                    }
                }
            }
            else -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .defaultMinSize(minHeight = 48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = paletteInfo.name, fontWeight = FontWeight.SemiBold)
                        Text(text = paletteInfo.colors.size.toString() + " " + stringResource(id = R.string.colors))
                    }
                    IconButton(onClick = { onPaletteClick.invoke(paletteInfo) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_open_24),
                            contentDescription = "Open"
                        )
                    }
                }
            }


        }
    }
}

@Preview
@Composable
private fun PreviewSavedPaletteCard() {
    SavedColorSchemeCard(
        paletteInfo = CustomColorScheme(
            colors = listOf(
                ColorInfo(value = Color(0xFFE53935).string(), name = "XD"),
                ColorInfo(value = Color(0xFF512DA8).string(), name = "XD"),
                ColorInfo(value = Color(0xFF43A047).string(), name = "XD"),
            ),
            name = "Test name of palette"
        ),
        onPaletteClick = {},
        onDeletePalette = {}
    )
}

@Preview
@Composable
private fun PreviewSavedPaletteCard2() {
    SavedColorSchemeCard(
        paletteInfo = CustomColorScheme(
            colors = listOf(
                ColorInfo(value = Color(0xFFE53935).string(), name = "XD"),
                ColorInfo(value = Color(0xFFE0A935).string(), name = "XD"),
                ColorInfo(value = Color(0xFFE59935).string(), name = "XD"),
                ColorInfo(value = Color(0xFF512DA8).string(), name = "XD"),
                ColorInfo(value = Color(0xFF562118).string(), name = "XD"),
                ColorInfo(value = Color(0xFF513511).string(), name = "XD"),
                ColorInfo(value = Color(0xFF431047).string(), name = "XD"),
                ColorInfo(value = Color(0xFF43A047).string(), name = "XD"),
                ColorInfo(value = Color(0xFF439047).string(), name = "XD"),
            ),
            name = "Test name of palette"
        ),
        onPaletteClick = {},
        onDeletePalette = {}
    )
}