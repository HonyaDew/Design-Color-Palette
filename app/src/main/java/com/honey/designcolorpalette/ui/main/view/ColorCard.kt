package com.honey.designcolorpalette.ui.main.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.designcolorpalette.R
import com.honey.designcolorpalette.extencion.color
import com.honey.designcolorpalette.extencion.string
import com.honey.designcolorpalette.extencion.toHexString
import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.Palette
import com.honey.designcolorpalette.ui.theme.colorSelect

@Composable
fun DcpColorCard(
    color: ColorInfo,
    onColorClick: (color: ColorInfo) -> Unit
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Card(
        colors = CardDefaults.cardColors(containerColor = colorSelect(90)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 128.dp, minWidth = 96.dp)
                .clickable { onColorClick.invoke(color) },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = 2.dp, color = colorSelect()),
            content = {},
            colors = CardDefaults.cardColors(containerColor = color.value.color()),
            elevation = CardDefaults.cardElevation(2.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = color.name,
                    color = colorSelect(inverse = true),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = color.value.color().toHexString(), color = colorSelect(inverse = true))
            }
            IconButton(onClick = {
                clipboardManager.setText(
                    AnnotatedString(buildString {
                        //In future here can be another export types
                        append(color.value.color().toHexString())
                    })
                )
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "CopyIcon",
                    modifier = Modifier.size(32.dp),
                    tint = colorSelect(inverse = true)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDcpColorCard() {
    DcpColorCard(
        color = ColorInfo(
            value = Color(0xFFF44336).string(),
            name = "500",
            palette = Palette.Material(subPalette = ColorOfMaterial.RED)
        ),
        onColorClick = {}
    )
}