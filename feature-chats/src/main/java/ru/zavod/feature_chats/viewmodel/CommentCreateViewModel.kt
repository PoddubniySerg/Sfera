package ru.zavod.feature_chats.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_chats.model.Comment
import javax.inject.Inject

class CommentCreateViewModel @Inject constructor() : ViewModel() {

    private val loadStateMutableStateFlow = MutableStateFlow<LoadStateApp?>(value = null)
    private val messageMutableStateFlow = MutableStateFlow<String?>(value = null)

    val loadStateStateFlow = loadStateMutableStateFlow.asStateFlow()
    val messagStateFlow = messageMutableStateFlow.asStateFlow()

    fun setMessage(message: String?) {
        messageMutableStateFlow.value = message
    }

    fun create(me: String, created: (Comment) -> Unit) {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                val comment = Comment(
                    message = messagStateFlow.value!!,
                    createdBy = me
                )
                messageMutableStateFlow.value = EMPTY_STRING
                created(comment)
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (e: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = e)
            }
        }
    }
}