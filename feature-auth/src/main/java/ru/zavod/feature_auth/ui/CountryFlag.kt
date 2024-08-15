package ru.zavod.feature_auth.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.simon.xmaterialccp.data.CountryData
import com.simon.xmaterialccp.data.utils.getFlags

@Composable
internal fun CountryFlag(country: CountryData){
    Image(
        painter = painterResource(id = getFlags(country.countryCode)),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}