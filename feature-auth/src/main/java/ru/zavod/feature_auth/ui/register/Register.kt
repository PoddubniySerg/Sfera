package ru.zavod.feature_auth.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simon.xmaterialccp.data.CountryData
import com.simon.xmaterialccp.data.utils.getLibCountries
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.app_core.model.Token
import ru.zavod.feature_auth.PaddingLvl1
import ru.zavod.feature_auth.R
import ru.zavod.feature_auth.di.AuthComponent
import ru.zavod.feature_auth.di.DeviceAuthRepository
import ru.zavod.feature_auth.di.RemoteAuthRepository
import ru.zavod.feature_auth.exception.InvalidUsernameException
import ru.zavod.feature_auth.model.CheckAuthCodeParams
import ru.zavod.feature_auth.model.RegisterParams
import ru.zavod.feature_auth.model.RegisterResult
import ru.zavod.feature_auth.model.SendAuthCodeParams
import ru.zavod.feature_auth.model.SendAuthCodeResult
import ru.zavod.feature_auth.ui.LoadState
import ru.zavod.feature_auth.ui.Phone
import ru.zavod.feature_auth.ui.TextInputTitle
import ru.zavod.feature_auth.usecase.RegisterUseCase
import ru.zavod.feature_auth.viewmodel.RegisterViewModel

@Composable
internal fun Register(
    viewModel: RegisterViewModel = getViewModel(),
    countryAlias: String?,
    phone: String?,
    onBack: () -> Unit
) {
    val loadState by viewModel.loadStateStateFlow.collectAsState()
    LoadState(loadState = loadState, success = {}, failed = {})
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = PaddingLvl1)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(space = PaddingLvl1)
    ) {
        val params by viewModel.paramsStateFlow.collectAsState()
        val country = getLibCountries().single { it.countryCode == countryAlias }
        LaunchedEffect(key1 = phone, key2 = countryAlias) {
            viewModel.setParams(params = params.copy(phone = "${country.countryPhoneCode}$phone"))
        }
        PhoneBox(country = country, phone = phone)
        Name(
            value = params.name,
            onValueChange = { viewModel.setParams(params = params.copy(name = it)) })
        UserName(
            loadState = loadState,
            value = params.username,
            onValueChange = { viewModel.setParams(params = params.copy(username = it)) })
        Buttons(
            loadState = loadState,
            confirm = viewModel::register,
            onBack = onBack
        )
    }
}

@Composable
private fun PhoneBox(country: CountryData, phone: String?) {
    Phone(
        country = country,
        code = country.countryPhoneCode,
        phone = phone ?: EMPTY_STRING,
        editable = false,
        error = false,
        openCountrySelector = {},
        oncodeChange = {},
        onPhoneChange = {}
    )
}

@Composable
private fun Name(value: String, onValueChange: (String) -> Unit) {
    Input(
        title = stringResource(id = R.string.input_name_title),
        value = value,
        onValueChange = onValueChange,
        placeHolder = stringResource(id = R.string.input_name_placeholder)
    )
}

@Composable
private fun ColumnScope.UserName(
    loadState: LoadStateApp?,
    value: String,
    onValueChange: (String) -> Unit
) {
    Input(
        title = stringResource(id = R.string.input_username_title),
        value = value,
        onValueChange = onValueChange,
        placeHolder = stringResource(id = R.string.input_username_placeholder),
        isError = loadState is LoadStateApp.Failed
                && loadState.throwable is InvalidUsernameException,
        supportingText = {
            Text(
                text = stringResource(id = R.string.input_username_help_message),
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}

@Composable
private fun Input(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    isError: Boolean = false,
    supportingText: @Composable() (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        isError = isError,
        modifier = Modifier.fillMaxWidth(),
        label = { TextInputTitle(text = title) },
        placeholder = { Text(text = placeHolder) },
        supportingText = supportingText
    )
}

@Composable
private fun Buttons(loadState: LoadStateApp?, confirm: () -> Unit, onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = confirm,
            enabled = loadState !is LoadStateApp.Failed
                    || loadState.throwable !is InvalidUsernameException
        ) {
            Text(text = stringResource(id = R.string.send_button_title))
        }
        OutlinedButton(onClick = onBack) {
            Text(text = stringResource(id = R.string.cancel_button_title))
        }
    }
}

@Composable
private fun getViewModel(): RegisterViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as AuthComponent).registerViewModelFactory()
    return viewModel<RegisterViewModel>(factory = viewModelFactory)
}

@Preview(showBackground = true)
@Composable
private fun ProfilePasswordErrorPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        val registerUseCase = RegisterUseCase(
            remoteAuthRepository = object : RemoteAuthRepository {
                override suspend fun sendAuthCode(params: SendAuthCodeParams): SendAuthCodeResult? {
                    return null
                }

                override suspend fun checkAuthCode(params: CheckAuthCodeParams): Token {
                    return Token(access = null, refresh = null, userExist = false)
                }

                override suspend fun register(params: RegisterParams): RegisterResult? {
                    return null
                }
            },
            deviceAuthRepository = object : DeviceAuthRepository {
                override suspend fun saveToken(token: Token) {
                }
            }
        )
        val viewModel = RegisterViewModel(registerUseCase = registerUseCase)
        Register(viewModel = viewModel, countryAlias = "ru", phone = "9250926152", onBack = {})
    }
}