package ru.zavod.feature_auth.ui.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simon.xmaterialccp.data.CountryData
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_auth.PaddingLvl1
import ru.zavod.feature_auth.R
import ru.zavod.feature_auth.di.AuthComponent
import ru.zavod.feature_auth.ui.LoadState
import ru.zavod.feature_auth.ui.Phone
import ru.zavod.feature_auth.ui.TextInputTitle
import ru.zavod.feature_auth.viewmodel.AuthViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Auth(
    viewModel: AuthViewModel = getViewModel(),
    register: (String, String) -> Unit,
    success: () -> Unit
) {
    val countries by viewModel.countriesStateFlow.collectAsState()
    val openDialog by viewModel.openDialogStateFlow.collectAsState()
    val selected by viewModel.selectedStateFlow.collectAsState()
    val code by viewModel.codeStateFlow.collectAsState()
    val phone by viewModel.phoneStateFlow.collectAsState()
    val loadState by viewModel.loadStateStateFlow.collectAsState()
    LoadState(loadState = loadState, success = {}, failed = { })
    var checkAuthLoadState by remember { mutableStateOf<LoadStateApp?>(value = null) }
    LoadState(loadState = checkAuthLoadState, success = {}, failed = { })
    OpenSearchDialog(
        openDialog = openDialog,
        close = { viewModel.openDialog(open = false) },
        select = viewModel::selectCountry
    )
    LazyColumn(
        modifier = Modifier.padding(all = PaddingLvl1),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        header(
            selected = selected,
            code = code,
            phone = phone,
            loadState = loadState,
            openCountrySelector = { viewModel.openDialog(open = !openDialog) },
            oncodeChange = viewModel::setCode,
            onPhoneChange = viewModel::setPhone
        )
        if (loadState is LoadStateApp.Success) {
            checkAuthCode(
                code = code,
                phone = phone,
                loadStateHandler = { checkAuthLoadState = it },
                register = { register(selected.countryCode, phone) },
                success = success,
                onBack = viewModel::changePhone
            )
        } else {
            countries(countries = countries, select = viewModel::selectCountry)
            buttons(onclick = viewModel::sendPhone)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.header(
    selected: CountryData,
    code: String,
    phone: String,
    loadState: LoadStateApp?,
    openCountrySelector: () -> Unit,
    oncodeChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit
) {
    stickyHeader {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Title()
            Phone(
                country = selected,
                code = code,
                phone = phone,
                editable = loadState == null || loadState is LoadStateApp.Failed,
                error = loadState is LoadStateApp.Failed,
                openCountrySelector = openCountrySelector,
                oncodeChange = oncodeChange,
                onPhoneChange = onPhoneChange
            )
            AuthError(loadState = loadState)
        }
    }
}

private fun LazyListScope.checkAuthCode(
    code: String,
    phone: String,
    loadStateHandler: (LoadStateApp?) -> Unit,
    register: () -> Unit,
    success: () -> Unit,
    onBack: () -> Unit
) {
    item {
        CheckAuthCode(
            phone = "$code$phone",
            loadStateHandler = loadStateHandler,
            register = register,
            success = success,
            onBack = onBack
        )
    }
}

private fun LazyListScope.buttons(onclick: () -> Unit) {
    item {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = onclick) {
                Text(text = stringResource(id = R.string.send_button_title))
            }
        }
    }
}

@Composable
private fun OpenSearchDialog(
    openDialog: Boolean,
    close: () -> Unit,
    select: (CountryData) -> Unit
) {
    if (!openDialog) {
        return
    }
    DialogCountrySelect(close = close, select = select)
}

@Composable
private fun Title() {
    TextInputTitle(text = stringResource(id = R.string.auth_header_title))
}

@Composable
private fun getViewModel(): AuthViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as AuthComponent).authViewModelFactory()
    return viewModel<AuthViewModel>(factory = viewModelFactory)
}