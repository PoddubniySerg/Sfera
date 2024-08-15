package ru.zavod.feature_chats.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavod.feature_chats.INPUT_COMMENT_MAX_LINES
import ru.zavod.feature_chats.PaddingLvl1
import ru.zavod.feature_chats.PaddingLvl2
import ru.zavod.feature_chats.R
import ru.zavod.feature_chats.RadiusLvl1
import ru.zavod.feature_chats.model.Comment
import ru.zavod.feature_chats.viewmodel.CommentCreateViewModel

@Composable
internal fun CommentCreate(created: (Comment) -> Unit) {
    val viewModel = viewModel<CommentCreateViewModel>()
    Params(viewModel = viewModel, created = created)
}

@Composable
private fun Params(
    viewModel: CommentCreateViewModel,
    created: (Comment) -> Unit
) {
    val message by viewModel.messagStateFlow.collectAsState()
    val meName = stringResource(id = R.string.me_name)
    Content(
        message = message,
        setMessage = viewModel::setMessage,
        create = { viewModel.create(me = meName, created = created) }
    )
}

@Composable
private fun Content(
    message: String?,
    setMessage: (String?) -> Unit,
    create: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(space = PaddingLvl1),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(all = PaddingLvl2)
    ) {
        Input(message = message, setMessage = setMessage)
        SendButton(onClick = create)
    }
}

@Composable
private fun RowScope.Input(message: String?, setMessage: (String?) -> Unit) {
    OutlinedTextField(
        value = message ?: "",
        onValueChange = setMessage,
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(size = RadiusLvl1),
        maxLines = INPUT_COMMENT_MAX_LINES
    )
}

@Composable
private fun SendButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.Send,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentCreatePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        CommentCreate(created = { })
    }
}