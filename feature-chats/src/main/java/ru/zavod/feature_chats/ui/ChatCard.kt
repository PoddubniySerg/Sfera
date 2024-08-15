package ru.zavod.feature_chats.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.zavod.app_core.FULL_WEIGTH
import ru.zavod.feature_chats.CHAT_CARD_LAST_MESSAGE_MAX_LINES
import ru.zavod.feature_chats.CHAT_CARD_TITLE_MAX_LINES
import ru.zavod.feature_chats.ContentSizeLvl1
import ru.zavod.feature_chats.PaddingLvl1
import ru.zavod.feature_chats.RadiusLvl1
import ru.zavod.feature_chats.model.Chat

@Composable
internal fun ChatCard(chat: Chat, onClick: (Chat) -> Unit) {
    ElevatedButton(
        onClick = { onClick(chat) },
        shape = RoundedCornerShape(size = RadiusLvl1)
    ) {
        Box(
            modifier = Modifier
                .size(size = ContentSizeLvl1)
                .background(color = chat.iconColor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(width = PaddingLvl1))
        Column(modifier = Modifier.weight(weight = FULL_WEIGTH)) {
            Title(chat = chat)
            Message(chat = chat)
        }
    }
}

@Composable
private fun Title(chat: Chat) {
    Text(
        text = chat.title,
        maxLines = CHAT_CARD_TITLE_MAX_LINES,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun Message(chat: Chat) {
    Text(
        text = chat.lastMessage,
        maxLines = CHAT_CARD_LAST_MESSAGE_MAX_LINES,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true)
@Composable
private fun ChatPreview() {
    ChatCard(
        chat = Chat(
            title = "Test title",
            lastMessage = "Last message of the chat",
            iconColor = Color.Red
        ), onClick = {})
}