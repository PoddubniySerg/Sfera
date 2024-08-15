package ru.zavod.feature_auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.zavod.app_core.model.LoadStateApp

@Composable
internal fun LoadState(
    loadState: LoadStateApp?,
    success: () -> Unit,
    failed: (Throwable?) -> Unit
) {
    when (loadState) {
        null -> Unit
        is LoadStateApp.Failed -> failed(loadState.throwable)
        LoadStateApp.Loading -> Loading()
        LoadStateApp.Success -> success()
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}