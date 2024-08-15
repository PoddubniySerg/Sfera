package ru.zavod.feature_auth.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.simon.xmaterialccp.data.CountryData
import com.simon.xmaterialccp.data.utils.getCountryName
import com.simon.xmaterialccp.data.utils.getDefaultLangCode
import com.simon.xmaterialccp.data.utils.getLibCountries
import ru.zavod.app_core.FULL_WEIGTH
import ru.zavod.feature_auth.PaddingLvl1
import ru.zavod.feature_auth.WidthLvl2
import ru.zavod.feature_auth.ui.CountryFlag

internal fun LazyListScope.countries(
    countries: List<CountryData>?,
    select: (CountryData) -> Unit
) {
    if (countries == null) {
        return
    }
    items(countries) {
        Country(country = it, onClick = { select(it) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Country(country: CountryData, onClick: () -> Unit) {
    TextButton(
        content = {
            CountryFlag(country = country)
            Spacer(modifier = Modifier.width(width = PaddingLvl1))
            Name(country = country)
            Spacer(modifier = Modifier.weight(weight = FULL_WEIGTH))
            Trailing(country = country)
        },
        onClick = onClick,
        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
    )
}

@Composable
private fun Name(country: CountryData) {
    Text(
        text = stringResource(id = getCountryName(country.countryCode.lowercase())),
        maxLines = 1,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.widthIn(max = WidthLvl2)
    )
}

@Composable
private fun Trailing(country: CountryData) {
    Text(
        text = country.countryPhoneCode,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Preview(showBackground = false)
@Composable
private fun IssueCardPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        Box(modifier = Modifier) {
            val context = LocalContext.current
            val defaultLang = getDefaultLangCode(context = context)
            val country = getLibCountries().single { it.countryCode == defaultLang }
            Country(country = country, onClick = {})
        }
    }
}