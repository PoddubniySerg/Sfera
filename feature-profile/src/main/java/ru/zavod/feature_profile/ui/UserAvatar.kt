package ru.zavod.feature_profile.ui

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
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
internal fun UserAvatar(user: User?, size: Dp, uri: Uri?) {
    when (uri) {
        null -> Avatar(user = user, size = size)
        else -> ImageByUri(imageUri = uri, avatarSize = size)
    }
}

@Composable
private fun Avatar(user: User?, size: Dp) {
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

@Suppress("DEPRECATION")
@Composable
private fun ImageByUri(imageUri: Uri, avatarSize: Dp) {
    Box(
        modifier = Modifier
            .size(size = avatarSize)
            .clip(shape = CircleShape)
            .background(color = MaterialTheme.colorScheme.outlineVariant)
    ) {
        val contentResolver = LocalContext.current.contentResolver
        val bitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images
                .Media.getBitmap(contentResolver, imageUri)
        } else {
            val source = ImageDecoder
                .createSource(contentResolver, imageUri)
            ImageDecoder.decodeBitmap(source)
        }
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}