package com.honyadew.designsystem.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.honyadew.designsystem.R
import com.honyadew.designsystem.theme.colorSelect
import kotlinx.coroutines.delay

@Composable
fun DcpAlertDialog(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    onEditTitle: ((newTitle: String) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current

    var editMode by remember { mutableStateOf(false) }

    AlertDialog(
        modifier = modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss.invoke() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
        title = {

            var textFieldValue by remember { mutableStateOf(title) }
            OutlinedTextField(
                value = if (editMode) textFieldValue else title,
                onValueChange = {textFieldValue = it},
                enabled = editMode,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = Color.Transparent,
                    unfocusedBorderColor = colorSelect(inverse = true, saturation = 70),
                    focusedBorderColor = colorSelect(inverse = true, saturation = 90),
                    disabledTextColor = colorSelect(inverse = true),
                    cursorColor = colorSelect(inverse = true, saturation = 90)
                ),
                trailingIcon = {
                    if (onEditTitle != null){
                        Icon(
                            painter = painterResource(
                                id = if (editMode) R.drawable.ic_save_24 else R.drawable.ic_edit
                            ),
                            contentDescription = "Edit and Save",
                            modifier.clickable {
                                if (editMode){
                                    onEditTitle.invoke(textFieldValue)
                                    onDismiss.invoke()
                                }
                                editMode = !editMode
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 22.sp)


            )
        },
        text = {
            Column {
                Divider()
                Box(modifier = Modifier.padding(vertical = 4.dp)) {
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