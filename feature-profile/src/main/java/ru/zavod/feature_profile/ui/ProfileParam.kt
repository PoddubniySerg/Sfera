package ru.zavod.feature_profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.feature_profile.PaddingLvl2
import ru.zavod.feature_profile.RadiusLvl1
import ru.zavod.feature_profile.model.ViewMode

@Composable
internal fun ProfileParam(
    mode: ViewMode,
    title: String,
    value: String?,
    editable: Boolean,
    isError: Boolean,
    onValueChange: (String?) -> Unit
) {
    Column {
        Title(mode = mode, title = title)
        when (mode) {
            ViewMode.VIEW -> ViewMode(value = value)
            ViewMode.EDIT -> EditMode(
                value = value,
                enabled = editable && mode == ViewMode.EDIT,
                isError = isError,
                onValueChange = onValueChange
            )
        }
    }
}

@Composable
private fun Title(mode: ViewMode, title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = when (mode) {
            ViewMode.EDIT -> MaterialTheme.colorScheme.outline
            ViewMode.VIEW -> MaterialTheme.colorScheme.outlineVariant
        },
        modifier = when (mode) {
            ViewMode.EDIT -> Modifier.padding(start = PaddingLvl2)
            ViewMode.VIEW -> Modifier
        }
    )
}

@Composable
private fun ViewMode(value: String?) {
    Text(
        text = value ?: EMPTY_STRING,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun EditMode(
    value: String?,
    enabled: Boolean,
    isError: Boolean,
    onValueChange: (String?) -> Unit
) {
    OutlinedTextField(
        value = value ?: EMPTY_STRING,
        onValueChange = onValueChange,
        enabled = enabled,
        shape = RoundedCornerShape(size = RadiusLvl1),
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        isError = isError,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfilePasswordViewModePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ProfileParam(
            mode = ViewMode.VIEW,
            title = "Example title",
            value = "Example value",
            editable = true,
            isError = false,
            onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePasswordEdidModePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ProfileParam(
            mode = ViewMode.EDIT,
            title = "Example title",
            value = "Example value",
            editable = true,
            isError = false,
            onValueChange = {})
    }
}