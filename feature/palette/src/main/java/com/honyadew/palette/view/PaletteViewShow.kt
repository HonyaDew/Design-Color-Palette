package com.honyadew.palette.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.palette.R
import com.honyadew.designsystem.view.DcpAlertDialog
import com.honyadew.designsystem.view.DcpColorCard
import com.honyadew.palette.contract.PaletteState
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.extencion.color
import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorOfMaterial
import com.honyadew.model.Palette

@Composable
fun PaletteViewShow(
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
                            if (state.colorsToShow.last() != colorInfo) {
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
                border = BorderStroke(
                    width = 2.dp,
                    color = state.palette.subPalette.preview.color()
                ),
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
    DcpAlertDialog(
        title = stringResource(id = R.string.colors_of_material),
        onDismiss = onDismiss
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 96.dp),
        ) {
            val colors = com.honyadew.model.ColorOfMaterial.values().toList()
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

    }
}

@Preview
@Composable
private fun PreviewPaletteView() {
    val colors = arrayListOf<ColorInfo>()
    com.honyadew.model.ColorOfMaterial.values().forEach { color ->
        colors.add(
            ColorInfo(
                value = color.preview,
                name = "testName",
                palette = Palette.Material(com.honyadew.model.ColorOfMaterial.RED)
            )
        )
    }
    PaletteViewShow(
        state = PaletteState.Show(
            colorsToShow = colors,
            palette = Palette.Material(ColorOfMaterial.RED)
        ),
        onColorClick = {},
        onSelectSubPalette = {}
    )
}