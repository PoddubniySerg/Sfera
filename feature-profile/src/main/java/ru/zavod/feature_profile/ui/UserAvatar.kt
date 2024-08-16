package ru.zavod.feature_profile.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.R

@Composable
fun UserAvatar(user: User?, size: Dp) {
    val imageRequestBuilder = ImageRequest.Builder(context = LocalContext.current)
        .data(data = user?.avatar)
        .crossfade(enable = true)
    val modifier = Modifier
        .size(size = size)
        .clip(shape = CircleShape)
    val placeholder = painterResource(id = R.drawable.user_avatar_placeholder_icon)
    var failed by remember { mutableStateOf(value = false) }
    if (failed) {
        AsyncImage(
            model = imageRequestBuilder.decoderFactory(SvgDecoder.Factory()).build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier,
            placeholder = placeholder,
            error = placeholder
        )
    } else {
        AsyncImage(
            model = imageRequestBuilder.build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier,
            placeholder = placeholder,
            error = placeholder,
            onError = { failed = true }
        )
    }
}