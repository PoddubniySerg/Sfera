package ru.zavod.feature_onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingViewModel @Inject constructor() : ViewModel() {

    private val finishMutableStateFlow = MutableStateFlow(value = false)
    val finishStateFlow = finishMutableStateFlow.asStateFlow()

    fun onboard(timeMillis: Long) {
        viewModelScope.launch {
            delay(timeMillis)
            finishMutableStateFlow.value = true
        }
    }
}