package ru.zavod.feature_auth.ui.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simon.xmaterialccp.data.CountryData
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.feature_auth.viewmodel.DialogCountrySelectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DialogCountrySelect(
    viewModel: DialogCountrySelectViewModel = getViewModel(),
    close: () -> Unit,
    select: (CountryData) -> Unit
) {
    val context = LocalContext.current.applicationContext
    val countries by viewModel.countriesStateFlow.collectAsState()
    val searchValue by viewModel.searchValueStateFlow.collectAsState()
    BackHandler(onBack = close)
    Dialog(
        onDismissRequest = close,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        SearchBar(
            query = searchValue,
            onQueryChange = { viewModel.search(searchValue = it, context = context) },
            onSearch = {},
            active = true,
            onActiveChange = {},
            leadingIcon = {
                IconButton(onClick = close) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        ) {
            LazyColumn {
                countries(
                    countries = countries,
                    select = {
                        viewModel.search(searchValue = EMPTY_STRING, context = context)
                        select(it)
                        close()
                    }
                )
            }
        }
    }
}

@Composable
private fun getViewModel(): DialogCountrySelectViewModel {
    return viewModel<DialogCountrySelectViewModel>()
}