package com.honey.designcolorpalette.ui.screen.palette.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.extencion.color
import com.honey.designcolorpalette.ui.main.view.DcpColorCard
import com.honey.designcolorpalette.ui.screen.palette.contract.PaletteState
import com.honey.designcolorpalette.ui.theme.colorSelect
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.Palette

@Composable
fun PaletteViewMain(
    state: PaletteState.Show,
    onColorClick: (color: ColorInfo) -> Unit,
    onSelectSubPalette: (palette: Palette) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 192.dp)) {
            state.colorsToShow.forEach { colorInfo ->
                item {
                    DcpColorCard(
                        color = colorInfo,
                        onColorClick = onColorClick,
                        modifier = Modifier.padding(
                            if (state.colorsToShow.last() != colorInfo){
                                PaddingValues(8.dp)
                            } else {
                                PaddingValues(
                                    bottom = 64.dp,
                                    top = 8.dp,
                                    start = 8.dp,
                                    end = 8.dp
                                )
                            }
                        )
                    )
                }
            }
        }


        //Sub Palette selector, only for Material Palette
        if (state.palette is Palette.Material) {
            val showDialog = remember { mutableStateOf(false) }
            OutlinedIconButton(
                onClick = { showDialog.value = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 12.dp, end = 12.dp)
                    .size(64.dp),
                border = BorderStroke(width = 2.dp, color = state.palette.subPalette.preview.color()),
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    containerColor = colorSelect(90),
                    contentColor = colorSelect(saturation = 90, inverse = true)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_border),
                    contentDescription = "Sub Palette"
                )
            }
            if (showDialog.value) {
                SubPaletteMaterialDialog(
                    onDismiss = { showDialog.value = false },
                    onSelectSubPalette = { palette ->
                        onSelectSubPalette.invoke(palette)
                        showDialog.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun SubPaletteMaterialDialog(
    onDismiss: () -> Unit,
    onSelectSubPalette: (palette: Palette) -> Unit
) {
    val configuration = LocalConfiguration.current

    AlertDialog(
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss.invoke() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
        title = {
            Text(text = stringResource(id = R.string.colors_of_material))
        },
        text = {
            Column {
                Divider(modifier = Modifier.weight(0.02f, false))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 96.dp),
                    modifier = Modifier
                        .weight(0.96f)
                        .padding(vertical = 4.dp)
                ) {
                    val colors = ColorOfMaterial.values().toList()
                    for (color in colors) {
                        item {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = color.preview.color()),
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 64.dp, minWidth = 96.dp)
                                    .padding(2.dp)
                                    .clickable {
                                        onSelectSubPalette.invoke(Palette.Material(color))
                                    },
                                content = {},
                                shape = RoundedCornerShape(8.dp),
                            )
                        }
                    }
                }
                Divider(modifier = Modifier.weight(0.02f, false))
            }
        },
        confirmButton = {
            Text(
                text = stringResource(id = R.string.ok),
                color = colorSelect(70, inverse = true),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss.invoke() }
            )
        }
    )

}

@Preview
@Composable
private fun PreviewPaletteView() {
    val colors = arrayListOf<ColorInfo>()
    ColorOfMaterial.values().forEach { color ->
        colors.add(
            ColorInfo(
                value = color.preview,
                name = "testName",
                palette = Palette.Material(ColorOfMaterial.RED)
            )
        )
    }
    PaletteViewMain(
        state = PaletteState.Show(
            colorsToShow = colors,
            palette = Palette.Material(ColorOfMaterial.RED)
        ),
        onColorClick = {},
        onSelectSubPalette = {}
    )
}