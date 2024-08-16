package ru.zavod.feature_settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.app_core.model.Configuration
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_settings.PaddingLvl2
import ru.zavod.feature_settings.PaddingLvl3
import ru.zavod.feature_settings.RadiusLvl1
import ru.zavod.feature_settings.di.SettingsComponent
import ru.zavod.feature_settings.viewmodel.SettingsViewModel

@Composable
fun Configuration(
    viewModel: SettingsViewModel = getViewModel()
) {
    val configuration by viewModel.configurationStateFlow.collectAsState()
    val confirmButtonEnabled by viewModel.confimButtonEnabledStateFlow.collectAsState()
    val logoutState by viewModel.logoutStateFlow.collectAsState()
    Logout(
        openDialog = logoutState,
        logout = viewModel::logout,
        close = { viewModel.setLogout(openDialog = false) })
    Content(
        configuration = configuration,
        setConfiguration = viewModel::setConfiguration,
        confirmButton = {
            ConfigurationConfirmButton(
                enabled = confirmButtonEnabled,
                onClick = viewModel::confirm
            )
        },
        logout = { viewModel.setLogout(openDialog = !logoutState) }
    )
    val loadState by viewModel.loadStateStateFlow.collectAsState()
    if (loadState is LoadStateApp.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun Content(
    configuration: Configuration?,
    setConfiguration: (Configuration?) -> Unit,
    confirmButton: @Composable () -> Unit,
    logout: () -> Unit
) {
    val padding = PaddingLvl2
    Scaffold(
        modifier = Modifier
            .padding(all = padding)
            .clip(shape = RoundedCornerShape(size = RadiusLvl1)),
        containerColor = MaterialTheme.colorScheme.surface,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { confirmButton() },
        bottomBar = { LogoutButton(onClick = logout) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .padding(all = padding)
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(space = PaddingLvl3)
            ) {
                ConfigurationThemeSelector(
                    configuration = configuration,
                    setConfiguration = setConfiguration
                )
            }
        }
    )
}

@Composable
private fun getViewModel(): SettingsViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as SettingsComponent).settingsViewModelFactory()
    return viewModel<SettingsViewModel>(factory = viewModelFactory)
}

@Preview
@Composable
private fun ConfigurationPreview() {
    Content(
        configuration = Configuration(),
        setConfiguration = {},
        confirmButton = { ConfigurationConfirmButton(enabled = true, onClick = {}) },
        logout = {}
    )
}