package com.honyadew.designsystem.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.honey.designsystem.R
import com.honyadew.designsystem.theme.colorSelect

@Composable
fun DcpAlertDialog(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current

    AlertDialog(
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss.invoke() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
        title = {
            Text(text = title)
        },
        text = {
            Column {
                Divider()
                Box(modifier = Modifier.padding(vertical = 4.dp)){
                    content.invoke()
                }
                Divider()
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