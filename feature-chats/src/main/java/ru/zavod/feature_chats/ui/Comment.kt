package ru.zavod.feature_chats.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.zavod.feature_chats.FRACTION_WIDTH_MESSAGE
import ru.zavod.feature_chats.PaddingLvl2
import ru.zavod.feature_chats.model.Comment

@Composable
internal fun Comment(comment: Comment, isAuthor: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = PaddingLvl2),
        horizontalAlignment = when{
            isAuthor -> Alignment.End
            else -> Alignment.Start
        }
    ) {
        Message(comment = comment, isAuthor = isAuthor)
        Text(
            text = comment.createdBy,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.surfaceDim
        )
    }
}

@Composable
private fun Message(comment: Comment, isAuthor: Boolean) {
    OutlinedTextField(
        value = comment.message,
        onValueChange = {},
        enabled = false,
        modifier = Modifier.fillMaxWidth(fraction = FRACTION_WIDTH_MESSAGE),
        colors = OutlinedTextFieldDefaults.colors(
            disabledContainerColor = when {
                isAuthor -> MaterialTheme.colorScheme.secondary
                else -> MaterialTheme.colorScheme.tertiaryContainer
            },
            disabledTextColor = when {
                isAuthor -> MaterialTheme.colorScheme.onSecondary
                else -> MaterialTheme.colorScheme.onTertiaryContainer
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AuthorCommentsPreview() {
    Comment(
        comment = Comment(message = "Тестовое сообщение", createdBy = "Петя Крошкин"),
        isAuthor = true
    )
}

@Preview(showBackground = true)
@Composable
private fun AnyCommentsPreview() {
    Comment(
        comment = Comment(message = "Тестовое сообщение", createdBy = "Петя Крошкин"),
        isAuthor = false
    )
}