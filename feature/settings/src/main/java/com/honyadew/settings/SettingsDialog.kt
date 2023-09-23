package com.honyadew.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.honyadew.settings.contract.SettingsEffect
import com.honyadew.settings.contract.SettingsEvent
import com.honyadew.settings.contract.SettingsState
import com.honyadew.settings.view.SettingsView
import com.honyadew.designsystem.theme.colorSelect
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsDialogRoute(
    onDismiss: () -> Unit,
) {
    val viewModel = getViewModel<SettingsViewModel>()

    SettingsDialog(
        onDismiss = onDismiss,
        state = viewModel.getViewState().collectAsState(),
        effect = viewModel.getEffect(),
        onEventSend = {event -> viewModel.obtainEvent(event)}
    )

}

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    state: State<SettingsState>,
    effect: SharedFlow<SettingsEffect?>,
    onEventSend: (event: SettingsEvent) -> Unit
) {
    val coroutine = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    AlertDialog(
        onDismissRequest = { onDismiss.invoke() },
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        properties = DialogProperties(usePlatformDefaultWidth = false),
        title = {
            Text(text = stringResource(id = R.string.settings))
        },
        text = {
            Column {
                Divider()
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    when(val state = state.value){
                        is SettingsState.Loading -> {
                            Text(text = stringResource(id = R.string.loading))
                        }
                        is SettingsState.Show -> {
                            SettingsView(
                                settings = state.settings,
                                onChangePalette = { palette ->
                                    onEventSend.invoke(SettingsEvent.SelectPalette(palette = palette))
                                },
                                onChangeThemeConfig = {themeConfig ->
                                    onEventSend.invoke(SettingsEvent.SelectTheme(themeConfig))
                                }
                            )
                        }
                    }
                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
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




    SideEffect {
        coroutine.launch {
            effect.collect(){effect ->
                //If I need to do something in viewModel after DismissClick, dismiss should invoke here
                when(effect){
                    else -> {}
                }
            }
        }

    }
}