package ru.zavod.feature_chats.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.EMPTY_STRING
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.app_core.model.User
import ru.zavod.feature_chats.model.Comment
import ru.zavod.feature_chats.model.GetUserParams
import ru.zavod.feature_chats.usecase.GetUserUseCase
import ru.zavod.feature_chats.utils.PagingItemsSource
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    companion object {
        private val LIMIT = 10
    }

    private val comments = mutableListOf<Comment>()
    private val userMutableStateFlow = MutableStateFlow<User?>(value = null)
    private val loadStateMutableStateFlow = MutableStateFlow<LoadStateApp?>(value = null)

    init {
        getUser()
    }

    val userStateFlow = userMutableStateFlow.asStateFlow()

    val pagedComments: Flow<PagingData<Comment>> = Pager(
        config = PagingConfig(LIMIT),
        pagingSourceFactory = {
            PagingItemsSource<Comment>(loadData = ::getComments)
        }
    ).flow

    fun initComments(user: User){
        repeat(times = 30) {
            val comment = Comment(
                message = "Тестовое сообщеие $it",
                createdBy = when {
                    it % 2 == 0 -> "Any User"
                    else -> user.name ?: EMPTY_STRING
                }
            )
            comments.add(element = comment)
        }
    }

    fun apend(comment: Comment) {
        comments.add(element = comment)
    }

    private suspend fun getComments(page: Int): List<Comment> {
        delay(timeMillis = 2000)
        return comments.reversed().drop(n = page * LIMIT).take(n = LIMIT)
    }

    private fun getUser() {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                val params = GetUserParams(id = null)
                userMutableStateFlow.value = getUserUseCase.execute(params = params)
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (e: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = e)
            }
        }
    }
}