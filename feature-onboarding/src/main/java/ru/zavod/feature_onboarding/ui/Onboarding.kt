package ru.zavod.feature_onboarding.ui

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavod.feature_onboarding.ANIMATION_TIME_MILLIS
import ru.zavod.feature_onboarding.viewmodel.OnboardingViewModel

@Composable
fun Onboarding(
    timeMillis: Long = ANIMATION_TIME_MILLIS,
    @RawRes animationId: Int? = null,
    content: (@Composable () -> Unit)? = null,
    start: () -> Unit
) {
    when {
        content == null && animationId == null -> return
        content == null -> animationId?.let { Loader(animationId = it) }
        else -> content()
    }
    val viewModel = viewModel<OnboardingViewModel>()
    val finish by viewModel.finishStateFlow.collectAsState()
    if (finish) start()
    viewModel.onboard(timeMillis = timeMillis)
}