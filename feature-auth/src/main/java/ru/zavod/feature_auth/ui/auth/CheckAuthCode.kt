package ru.zavod.feature_auth.ui.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_auth.PaddingLvl1
import ru.zavod.feature_auth.R
import ru.zavod.feature_auth.di.AuthComponent
import ru.zavod.feature_auth.ui.TextInputTitle
import ru.zavod.feature_auth.viewmodel.CheckAuthCodeViewModel

@Composable
internal fun CheckAuthCode(
    viewModel: CheckAuthCodeViewModel = getViewModel(),
    phone: String,
    loadStateHandler: (LoadStateApp?) -> Unit,
    register: () -> Unit,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)
    val loadState by viewModel.loadStateStateFlow.collectAsState()
    LaunchedEffect(key1 = loadState) {
        loadStateHandler(loadState)
    }
    val code by viewModel.codeStateFlow.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = PaddingLvl1)
    ) {
        Title()
        Input(
            value = code,
            enabled = loadState !is LoadStateApp.Loading,
            onValueChange = viewModel::setCode
        )
        AuthError(loadState = loadState)
        Buttons(
            confirm = { viewModel.sendCode(phone = phone, register = register) },
            onBack = onBack
        )
    }
}

@Composable
private fun Title() {
    TextInputTitle(text = stringResource(id = R.string.check_auth_code_title))
}

@Composable
private fun Input(
    value: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword,
            autoCorrect = true
        )
    )
}

@Composable
private fun Buttons(confirm: () -> Unit, onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = confirm) {
            Text(text = stringResource(id = R.string.send_button_title))
        }
        OutlinedButton(onClick = onBack) {
            Text(text = stringResource(id = R.string.cancel_button_title))
        }
    }
}

@Composable
private fun getViewModel(): CheckAuthCodeViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as AuthComponent).checkAuthCodeViewModelFactory()
    return viewModel<CheckAuthCodeViewModel>(factory = viewModelFactory)
}