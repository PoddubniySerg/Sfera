package ru.zavod.feature_chats.di

import ru.zavod.app_core.viewmodel.ViewModelFactory
import ru.zavod.feature_chats.viewmodel.ChatViewModel
import ru.zavod.feature_chats.viewmodel.ChatsViewModel

interface ChatsComponent {
    fun chatsViewModelFactory(): ViewModelFactory<ChatsViewModel>
    fun chatViewModelFactory(): ViewModelFactory<ChatViewModel>
}