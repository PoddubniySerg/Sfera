package ru.zavod.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_auth.model.CheckAuthCodeParams
import ru.zavod.feature_auth.usecase.CheckAuthCodeUseCase
import javax.inject.Inject

class CheckAuthCodeViewModel @Inject constructor(
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase
) : ViewModel() {

    private val loadStateMutableStateFlow = MutableStateFlow<LoadStateApp?>(value = null)
    private val codeMutableStateFlow = MutableStateFlow(value = EMPTY_STRING)

    val loadStateStateFlow = loadStateMutableStateFlow.asStateFlow()
    val codeStateFlow = codeMutableStateFlow.asStateFlow()

    fun setCode(code: String) {
        codeMutableStateFlow.value = code
    }

    fun sendCode(phone: String, register: () -> Unit, success: () -> Unit) {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                val params = CheckAuthCodeParams(phone = phone, code = codeStateFlow.value)
                val exist = checkAuthCodeUseCase.execute(params = params)
                codeMutableStateFlow.value = EMPTY_STRING
                when {
                    exist -> success()
                    else -> register()
                }
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (e: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = e)
            }
        }
    }
}