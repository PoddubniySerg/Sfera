package ru.zavod.feature_chats.di

import ru.zavod.app_core.viewmodel.ViewModelFactory
import ru.zavod.feature_chats.viewmodel.ChatViewModel

interface ChatsComponent {
    fun chatViewModelFactory(): ViewModelFactory<ChatViewModel>
}