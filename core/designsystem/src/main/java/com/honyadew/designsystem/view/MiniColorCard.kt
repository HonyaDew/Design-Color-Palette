package com.honyadew.designsystem.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.extencion.color
import com.honyadew.extencion.string
import com.honyadew.extencion.toHexString
import com.honyadew.model.ColorInfo

@Composable
fun MiniColorCard(
    color: com.honyadew.model.ColorInfo,
    modifier: Modifier = Modifier,
    hexFontWeight: TextUnit = 14.sp,
    clipboardManager: ClipboardManager = LocalClipboardManager.current
) {
    Card(
        modifier = modifier
            .widthIn(min = 64.dp, max = 128.dp)
            .clickable {
                clipboardManager.setText(
                    AnnotatedString(buildString {
                        append(
                            color.value
                                .color()
                                .toHexString()
                        )
                    })
                )
            },
        colors = CardDefaults.cardColors(containerColor = colorSelect(90)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = 2.dp, color = colorSelect()),
            colors = CardDefaults.cardColors(containerColor = color.value.color()),
            elevation = CardDefaults.cardElevation(2.dp),
            content = {}
        )
        Text(
            text = color.value.color().toHexString(),
            color = colorSelect(inverse = true),
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 4.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = hexFontWeight
        )
        if (color.name.isNotEmpty()){
            Text(
                text = color.name,
                color = colorSelect(inverse = true),
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 4.dp),
                maxLines = 1,
                fontSize = 10.sp
            )
        }

    }
}

@Preview
@Composable
private fun PreviewSavedMiniColorCard(){
    MiniColorCard(
        color = ColorInfo(
            value = Color.Green.string(),
            name = "on dark vibrant"
        )
    )
}