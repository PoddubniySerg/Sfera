package ru.zavod.feature_auth.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.simon.xmaterialccp.data.CountryData
import ru.zavod.feature_auth.BorderWidthLvl1
import ru.zavod.feature_auth.PaddingLvl1
import ru.zavod.feature_auth.RadiusLvl1
import ru.zavod.feature_auth.WidthLvl1
import ru.zavod.feature_auth.utils.PhoneMaskTransformation

@Composable
internal fun Phone(
    country: CountryData,
    code: String,
    phone: String,
    editable: Boolean = true,
    error: Boolean = false,
    openCountrySelector: () -> Unit,
    oncodeChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit
) {
    Box(
        modifier = Modifier.border(
            width = BorderWidthLvl1,
            shape = RoundedCornerShape(size = RadiusLvl1),
            color = when (error) {
                true -> MaterialTheme.colorScheme.error
                else -> Color.Transparent
            }
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = PaddingLvl1)
        ) {
            CountryFlag(country = country)
            DropDownButton(enabled = editable, onClick = openCountrySelector)
            Code(code = code, enabled = editable, onValueChange = oncodeChange)
            Phone(phone = phone, enabled = editable, onValueChange = onPhoneChange)
        }
    }
}

@Composable
private fun DropDownButton(enabled: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "arrow down"
        )
    }
}

@Composable
private fun Code(code: String, enabled: Boolean, onValueChange: (String) -> Unit) {
    Input(
        modifier = Modifier.width(width = WidthLvl1),
        value = code,
        enabled = enabled,
        onValueChange = onValueChange
    )
}

@Composable
private fun Phone(phone: String, enabled: Boolean, onValueChange: (String) -> Unit) {
    val maskTransformation = PhoneMaskTransformation()
    Input(
        value = phone,
        enabled = enabled,
        visualTransformation = maskTransformation,
        onValueChange = onValueChange,
        placeHolder = { Text(text = maskTransformation.mask, maxLines = 1) }
    )
}

@Composable
private fun Input(
    modifier: Modifier = Modifier,
    value: String,
    enabled: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    placeHolder: @Composable() (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        visualTransformation = visualTransformation,
        placeholder = placeHolder,
        enabled = enabled,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword,
            autoCorrect = true
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}