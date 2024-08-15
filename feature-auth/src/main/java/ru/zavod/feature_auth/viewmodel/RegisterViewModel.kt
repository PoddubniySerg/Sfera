package ru.zavod.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_auth.exception.InvalidUsernameException
import ru.zavod.feature_auth.model.RegisterParams
import ru.zavod.feature_auth.usecase.RegisterUseCase
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val loadStateMutableStateFlow = MutableStateFlow<LoadStateApp?>(value = null)
    private val paramsMutableStateFlow = MutableStateFlow(value = RegisterParams())

    val loadStateStateFlow = loadStateMutableStateFlow.asStateFlow()
    val paramsStateFlow = paramsMutableStateFlow.asStateFlow()

    fun setParams(params: RegisterParams) {
        paramsMutableStateFlow.value = params
        validateUsername(username = params.username)
    }

    fun register() {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                registerUseCase.execute(params = paramsStateFlow.value)
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (e: Exception) { loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = e)
            }
        }
    }

    private fun validateUsername(username: CharSequence) {
        val invalid = username.chars().anyMatch {
            it !in 'a'.code..'z'.code
                    && it !in 'A'.code..'Z'.code
                    && it !in '0'.code .. '9'.code
                    && it != '-'.code
                    && it != '_'.code
        }
        if (username.isBlank() || username.isEmpty() || invalid) {
            val throwable = InvalidUsernameException()
            loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = throwable)
        } else {
            loadStateMutableStateFlow.value = null
        }
    }
}