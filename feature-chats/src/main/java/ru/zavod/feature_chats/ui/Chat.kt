package ru.zavod.feature_chats.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.app_core.model.User
import ru.zavod.feature_chats.PaddingLvl1
import ru.zavod.feature_chats.PaddingLvl2
import ru.zavod.feature_chats.R
import ru.zavod.feature_chats.TonalElevationLvl1
import ru.zavod.feature_chats.di.ChatsComponent
import ru.zavod.feature_chats.di.ChatsDeviceRepository
import ru.zavod.feature_chats.di.ChatsRemoteRepository
import ru.zavod.feature_chats.model.Comment
import ru.zavod.feature_chats.model.GetUserParams
import ru.zavod.feature_chats.preview.SourceUser
import ru.zavod.feature_chats.usecase.GetUserUseCase
import ru.zavod.feature_chats.viewmodel.ChatViewModel

@Composable
fun Chat(viewModel: ChatViewModel = getViewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        tonalElevation = TonalElevationLvl1
    ) {
        val user by viewModel.userStateFlow.collectAsState()
        val comments = viewModel.pagedComments.collectAsLazyPagingItems()
        val name = stringResource(id = R.string.me_name)
        LaunchedEffect(key1 = user) {
            user?.let { viewModel.initComments(user = it.copy(name = name)) }
            comments.refresh()
        }
        Scaffold(
            modifier = Modifier.padding(horizontal = PaddingLvl2),
            containerColor = MaterialTheme.colorScheme.onPrimary,
            bottomBar = {
                CommentCreate(
                    created = {
                        viewModel.apend(comment = it)
                        comments.refresh()
                    }
                )
            }
        ) { paddingValues ->
            Comments(
                comments = comments,
                paddingValues = paddingValues,
                user = user?.copy(name = stringResource(id = R.string.me_name))
            )
        }
    }
}

@Composable
private fun Comments(
    comments: LazyPagingItems<Comment>,
    paddingValues: PaddingValues,
    user: User?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
        verticalArrangement = Arrangement.spacedBy(space = PaddingLvl1),
        reverseLayout = true
    ) {
        items(count = comments.itemCount) { index ->
            val comment = comments[index]
            if (comment != null) {
                Comment(comment = comment, isAuthor = comment.createdBy == user?.name)
            }
        }
        loadStateApply(lazyPagingItems = comments)
    }
}

@Composable
private fun getViewModel(): ChatViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as ChatsComponent).chatViewModelFactory()
    return viewModel<ChatViewModel>(factory = viewModelFactory)
}

@Preview(showBackground = true)
@Composable
private fun ChatPreview() {
    val storage = object : ChatsDeviceRepository {
        override suspend fun getUser(params: GetUserParams): User? {
            return SourceUser.getUserDetails()
        }

        override suspend fun saveUser(user: User) {
        }
    }
    val api = object : ChatsRemoteRepository {
        override suspend fun getUser(params: GetUserParams): User? {
            return SourceUser.getUserDetails()
        }
    }
    val useCase = GetUserUseCase(remoteRepository = api, deviceRepository = storage)
    Chat(viewModel = ChatViewModel(getUserUseCase = useCase))
}