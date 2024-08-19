package ru.zavod.feature_profile.viewmodel

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.model.GetUserParams
import ru.zavod.feature_profile.model.UpdateUserParams
import ru.zavod.feature_profile.model.ViewMode
import ru.zavod.feature_profile.usecase.GetUserUseCase
import ru.zavod.feature_profile.usecase.UpdateUserUseCase
import ru.zavod.feature_profile.utils.toUpdateParams
import java.util.Base64
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val userMutableStateFlow = MutableStateFlow<User?>(value = null)

    private val paramsMutableStateFlow = MutableStateFlow<User?>(value = null)
    val paramsStateFlow = paramsMutableStateFlow.asStateFlow()

    private val loadStateMutableStateFlow = MutableStateFlow<LoadStateApp?>(value = null)
    val loadStateStateFlow = loadStateMutableStateFlow.asStateFlow()

    private val viewModeMutableStateFlow = MutableStateFlow(value = ViewMode.VIEW)
    val viewModeStateFlow = viewModeMutableStateFlow.asStateFlow()

    private val buttonEnabledMutableStateFlow = MutableStateFlow(value = true)
    val buttonEnabledStateFlow = buttonEnabledMutableStateFlow.asStateFlow()

    private val selectedUriMutableStateFlow = MutableStateFlow<Uri?>(value = null)
    val selectedUriStateFlow = selectedUriMutableStateFlow.asStateFlow()

    private val avatarMutableStateFlow = MutableStateFlow<UpdateUserParams.Avatar?>(value = null)

    init {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                val user = getUserUseCase.execute(params = GetUserParams(id = null))
                userMutableStateFlow.value = user
                paramsMutableStateFlow.value = user
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (ex: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = ex)
            }
        }
    }

    fun setViewMode(mode: ViewMode) {
        viewModeMutableStateFlow.value = mode
        if (mode == ViewMode.VIEW) {
            refreshUser(user = paramsStateFlow.value)
            selectedUriMutableStateFlow.value = null
        }
        setButtonEnabled()
    }

    fun setUser(user: User?) {
        if (user == null) return
        paramsMutableStateFlow.value = user
        setButtonEnabled()
    }

    fun selectAvatar(uri: Uri?, context: Context) {
        if (uri == null) {
            return
        }
        selectedUriMutableStateFlow.value = uri
        val filename = getFileName(context = context, uri = uri) ?: return
        context.contentResolver?.openInputStream(uri)?.use {
            val bytes = it.readBytes()
            val base64 = Base64.getEncoder().encodeToString(bytes)
            val avatar = UpdateUserParams.Avatar(filename = filename, base64 = base64)
            avatarMutableStateFlow.value = avatar
            buttonEnabledMutableStateFlow.value = compareUsers()
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                val updated = updateUserUseCase.execute(
                    params = paramsMutableStateFlow.value?.toUpdateParams(
                        avatar = avatarMutableStateFlow.value
                    )
                )
                refreshUser(user = updated)
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (ex: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = ex)
                refreshUser(user = null)
            }
        }
    }

    private fun getFileName(context: Context, uri: Uri): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val index = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (index != null && index >= 0) {
            val fileName = cursor.getString(index)
            cursor.close()
            return fileName
        }
        return null
    }

    private fun refreshUser(user: User?) {
        if (user != null) {
            userMutableStateFlow.value = user
        }
        paramsMutableStateFlow.value = userMutableStateFlow.value
        loadStateMutableStateFlow.value = null
        setButtonEnabled()
    }

    private fun setButtonEnabled() {
        buttonEnabledMutableStateFlow.value = when {
            viewModeStateFlow.value == ViewMode.VIEW -> true
            viewModeStateFlow.value == ViewMode.EDIT -> compareUsers()
            else -> false
        }
    }

    private fun compareUsers(): Boolean {
        return userMutableStateFlow.value != paramsMutableStateFlow.value
                || avatarMutableStateFlow.value != null
    }
}