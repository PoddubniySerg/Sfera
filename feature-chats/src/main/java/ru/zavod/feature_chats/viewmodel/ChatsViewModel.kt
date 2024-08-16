package ru.zavod.feature_chats.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.zavod.feature_chats.model.Chat
import ru.zavod.feature_chats.model.GetUserParams
import ru.zavod.feature_chats.usecase.GetUserUseCase
import ru.zavod.feature_chats.utils.PagingItemsSource
import javax.inject.Inject

class ChatsViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    companion object {
        private val LIMIT = 10
    }

    init {
        viewModelScope.launch {
            try {
                getUserUseCase.execute(params = GetUserParams(id = null))
            } catch (_: Exception) {
            }
        }
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