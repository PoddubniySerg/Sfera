package ru.zavod.feature_auth.ui.auth

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_auth.R
import ru.zavod.feature_auth.exception.InvalidPhoneException
import ru.zavod.feature_auth.exception.UnauthorizedException

@Composable
internal fun AuthError(loadState: LoadStateApp?) {
    val throwable = when {
        loadState is LoadStateApp.Failed -> loadState.throwable ?: return
        else -> return
    }
    val messageId = when (throwable) {
        is UnauthorizedException -> R.string.unauthorized_error_message
        is InvalidPhoneException -> R.string.invalid_phone_error_message
        else -> R.string.unknown_error_message
    }
    Text(text = stringResource(id = messageId), color = MaterialTheme.colorScheme.error)
}