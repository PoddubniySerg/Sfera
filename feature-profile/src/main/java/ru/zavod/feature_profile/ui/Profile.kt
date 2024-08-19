package ru.zavod.feature_profile.ui

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.AvatarSizeLvl1
import ru.zavod.feature_profile.PaddingLvl1
import ru.zavod.feature_profile.PaddingLvl2
import ru.zavod.feature_profile.R
import ru.zavod.feature_profile.RadiusLvl2
import ru.zavod.feature_profile.SpaceVerticalLvl1
import ru.zavod.feature_profile.di.ProfileComponent
import ru.zavod.feature_profile.model.ViewMode
import ru.zavod.feature_profile.utils.getZodiac
import ru.zavod.feature_profile.viewmodel.ProfileViewModel

@Composable
fun Profile(viewModel: ProfileViewModel = getViewModel()) {
    val user by viewModel.paramsStateFlow.collectAsState()
    val viewMode by viewModel.viewModeStateFlow.collectAsState()
    val buttonEnabled by viewModel.buttonEnabledStateFlow.collectAsState()
    val loadState by viewModel.loadStateStateFlow.collectAsState()
    val uri by viewModel.selectedUriStateFlow.collectAsState()
    val context = LocalContext.current.applicationContext
    Content(
        user = user,
        viewMode = viewMode,
        isError = loadState is LoadStateApp.Failed,
        uri = uri,
        save = viewModel::setUser,
        selectUri = { viewModel.selectAvatar(uri = it, context = context) },
        confirmButton = {
            ProfileConfirmButton(
                viewMode = viewMode,
                enabled = buttonEnabled,
                setViewMode = viewModel::setViewMode,
                updateUser = viewModel::updateUser
            )
        }
    )
    if (viewMode == ViewMode.EDIT) {
        BackHandler(onBack = { viewModel.setViewMode(mode = ViewMode.VIEW) })
    }
    Loading(loadState = loadState)
}

@Composable
private fun Loading(loadState: LoadStateApp?) {
    if (loadState !is LoadStateApp.Loading) {
        return
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Content(
    user: User?,
    viewMode: ViewMode,
    isError: Boolean,
    uri: Uri?,
    save: (User?) -> Unit,
    selectUri: (Uri?) -> Unit,
    confirmButton: @Composable () -> Unit
) {
    val padding = PaddingLvl2
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    Scaffold(
        modifier = Modifier
            .padding(all = padding)
            .clip(shape = RoundedCornerShape(size = RadiusLvl2))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { focusManager.clearFocus() }
            ),
        containerColor = MaterialTheme.colorScheme.surface,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .padding(all = padding)
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(space = SpaceVerticalLvl1)
            ) {
                Avatar(
                    user = user,
                    enabled = viewMode == ViewMode.EDIT,
                    uri = uri,
                    selectUri = selectUri
                )
                Phone(mode = viewMode, user = user, isError = isError)
                Name(mode = viewMode, user = user, isError = isError)
                City(mode = viewMode, user = user, isError = isError, save = save)
                Birthday(mode = viewMode, user = user, isError = isError, save = save)
                Zodiac(mode = viewMode, user = user, isError = isError)
                Status(mode = viewMode, user = user, isError = isError, save = save)
                confirmButton()
            }
        }
    )
}

@Composable
private fun Phone(mode: ViewMode, user: User?, isError: Boolean) {
    ProfileParam(
        mode = mode,
        title = stringResource(id = R.string.profile_phone_title),
        value = user?.phone,
        editable = false,
        isError = isError,
        onValueChange = { }
    )
}

@Composable
private fun Name(mode: ViewMode, user: User?, isError: Boolean) {
    ProfileParam(
        mode = mode,
        title = stringResource(id = R.string.profile_name_title),
        value = user?.name,
        editable = false,
        isError = isError,
        onValueChange = { }
    )
}

@Composable
private fun City(mode: ViewMode, user: User?, isError: Boolean, save: (User?) -> Unit) {
    ProfileParam(
        mode = mode,
        title = stringResource(id = R.string.profile_city_title),
        value = user?.city,
        editable = true,
        isError = isError,
        onValueChange = { city ->
            setChanged(
                user = user,
                copy = { it.copy(city = city) },
                save = save
            )
        }
    )
}

@Composable
private fun Birthday(mode: ViewMode, user: User?, isError: Boolean, save: (User?) -> Unit) {
    ProfileParam(
        mode = mode,
        title = stringResource(id = R.string.profile_birthday_title),
        value = user?.birthday,
        editable = true,
        isError = isError,
        onValueChange = { birthday ->
            setChanged(
                user = user,
                copy = { it.copy(birthday = birthday) },
                save = save
            )
        }
    )
}

@Composable
private fun Zodiac(mode: ViewMode, user: User?, isError: Boolean) {
    val zodiac = getZodiac(birthday = user?.birthday ?: EMPTY_STRING)
    ProfileParam(
        mode = mode,
        title = stringResource(id = R.string.profile_zodiac_title),
        value = zodiac?.titleId?.let { stringResource(id = it) },
        editable = false,
        isError = isError,
        onValueChange = { }
    )
}

@Composable
private fun Status(mode: ViewMode, user: User?, isError: Boolean, save: (User?) -> Unit) {
    ProfileParam(
        mode = mode,
        title = stringResource(id = R.string.profile_about_me_title),
        value = user?.status,
        editable = true,
        isError = isError,
        onValueChange = { status ->
            setChanged(
                user = user,
                copy = { it.copy(status = status) },
                save = save
            )
        }
    )
}


private const val IMAGE_PREFIX = "image/*"

@Composable
private fun Avatar(user: User?, enabled: Boolean, uri: Uri?, selectUri: (Uri?) -> Unit) {
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { selected -> selected?.let { selectUri(it) } }
    )
    TextButton(
        onClick = { imageLauncher.launch(IMAGE_PREFIX) },
        contentPadding = PaddingValues(all = PaddingLvl1),
        shape = CircleShape,
        enabled = enabled
    ) {
        UserAvatar(user = user, size = AvatarSizeLvl1, uri = uri)
    }
}

@Composable
private fun getViewModel(): ProfileViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as ProfileComponent).profileViewModelFactory()
    return viewModel<ProfileViewModel>(factory = viewModelFactory)
}

private fun setChanged(user: User?, copy: (User) -> User, save: (User?) -> Unit) {
    if (user == null) {
        return
    }
    val updated = copy(user)
    save(updated)
}

@Preview(showBackground = true)
@Composable
private fun ProfileViewModePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        val mode = ViewMode.VIEW
        Content(
            user = User,
            viewMode = mode,
            isError = false,
            uri = null,
            save = {},
            selectUri = {},
            confirmButton = {
                ProfileConfirmButton(
                    viewMode = mode,
                    enabled = true,
                    setViewMode = {},
                    updateUser = {}
                )
            }
        )
    }
}

@Preview
@Composable
private fun ProfileEditModePreview() {
    val mode = ViewMode.EDIT
    Content(
        user = User,
        viewMode = mode,
        isError = true,
        uri = null,
        save = {},
        selectUri = {},
        confirmButton = {
            ProfileConfirmButton(
                viewMode = mode,
                enabled = true,
                setViewMode = {},
                updateUser = {}
            )
        }
    )
}

private val User = User(
    id = "id_preview",
    username = "username",
    name = "Семен",
    avatar = null,
    birthday = "1988-11-23",
    city = "Best City",
    status = null,
    phone = "+79250946152"
)