package ru.zavod.feature_profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.zavod.app_core.FULL_WEIGTH
import ru.zavod.feature_profile.R
import ru.zavod.feature_profile.model.ViewMode
import ru.zavod.feature_profile.PaddingLvl3
import ru.zavod.feature_profile.WidthLvl1
import ru.zavod.feature_profile.WidthLvl2

@Composable
internal fun ProfileConfirmButton(
    viewMode: ViewMode,
    enabled: Boolean,
    setViewMode: (ViewMode) -> Unit,
    updateUser: () -> Unit
) {
    Button(
        onClick = {
            onClick(
                viewMode = viewMode,
                setViewMode = setViewMode,
                updateUser = updateUser
            )
        },
        enabled = enabled,
        modifier = Modifier
            .widthIn(
                min = WidthLvl1,
                max = WidthLvl2
            )
            .padding(horizontal = PaddingLvl3)
    ) {
        LeadingIcon(viewMode = viewMode)
        Spacer(modifier = Modifier.weight(weight = FULL_WEIGTH))
        Title(viewMode = viewMode)
        Spacer(modifier = Modifier.weight(weight = FULL_WEIGTH))
    }
}

@Composable
private fun LeadingIcon(viewMode: ViewMode) {
    val icon = when (viewMode) {
        ViewMode.VIEW -> Icons.Rounded.Edit
        ViewMode.EDIT -> Icons.Rounded.Check
    }
    Icon(imageVector = icon, contentDescription = null)
}

@Composable
private fun Title(viewMode: ViewMode) {
    val title = when (viewMode) {
        ViewMode.VIEW -> stringResource(id = R.string.profile_select_edit_mode_button_title)
        ViewMode.EDIT -> stringResource(id = R.string.profile_update_button_title)
    }
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge
    )
}

private fun onClick(
    viewMode: ViewMode,
    setViewMode: (ViewMode) -> Unit,
    updateUser: () -> Unit
) {
    when (viewMode) {

        ViewMode.VIEW -> setViewMode(ViewMode.EDIT)

        ViewMode.EDIT -> {
            setViewMode(ViewMode.VIEW)
            updateUser()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileConfirmButtonViewModePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ProfileConfirmButton(
            viewMode = ViewMode.VIEW,
            enabled = true,
            setViewMode = {},
            updateUser = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileConfirmButtonViewModeDisabledPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ProfileConfirmButton(
            viewMode = ViewMode.VIEW,
            enabled = false,
            setViewMode = {},
            updateUser = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileConfirmButtonEditModePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ProfileConfirmButton(
            viewMode = ViewMode.EDIT,
            enabled = true,
            setViewMode = {},
            updateUser = {})
    }
}