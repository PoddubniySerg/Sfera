package ru.zavod.feature_chats.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.feature_chats.PaddingLvl1
import ru.zavod.feature_chats.TonalElevationLvl1
import ru.zavod.feature_chats.di.ChatsComponent
import ru.zavod.feature_chats.model.Chat
import ru.zavod.feature_chats.viewmodel.ChatViewModel
import ru.zavod.feature_chats.viewmodel.ChatsViewModel

@Composable
internal fun Chats(
    viewModel: ChatsViewModel = getViewModel(),
    onChatClick: (Chat) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        tonalElevation = TonalElevationLvl1
    ) {
        val chats = viewModel.pagedChats.collectAsLazyPagingItems()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(space = PaddingLvl1)
        ) {
            items(chats.itemCount) {
                val chat = chats[it]
                if (chat != null) {
                    ChatCard(chat = chat, onClick = onChatClick)
                }
            }
        }
    }
}

@Composable
private fun getViewModel(): ChatsViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as ChatsComponent).chatsViewModelFactory()
    return viewModel<ChatsViewModel>(factory = viewModelFactory)
}

@Preview(showBackground = true)
@Composable
private fun ChatsPreview() {
    Chats(onChatClick = {})
}