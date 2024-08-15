package ru.zavod.feature_chats.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.zavod.feature_chats.model.Chat
import ru.zavod.feature_chats.utils.PagingItemsSource
import javax.inject.Inject

class ChatsViewModel @Inject constructor() : ViewModel() {

    companion object {
        private val LIMIT = 10
    }

    private val chats = mutableListOf<Chat>()

    init {
        val colors = listOf(Color.Red, Color.Blue, Color.Cyan, Color.Green)
        repeat(times = 10) {
            val chat = Chat(
                title = "Any Title $it",
                lastMessage = "Last message $it",
                iconColor = colors[it % colors.size]
            )
            chats.add(element = chat)
        }
    }

    val pagedChats: Flow<PagingData<Chat>> = Pager(
        config = PagingConfig(LIMIT),
        pagingSourceFactory = {
            PagingItemsSource<Chat>(loadData = { chats.drop(n = it).take(n = LIMIT) })
        }
    ).flow
}