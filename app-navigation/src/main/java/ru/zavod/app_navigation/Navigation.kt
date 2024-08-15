package ru.zavod.app_navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.zavod.app_core.di.AppComponentProvider
import ru.zavod.app_core.model.Configuration
import ru.zavod.app_core.model.Token
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.NavigationComponent
import ru.zavod.app_navigation.di.OnboardingParams
import ru.zavod.app_navigation.ui.BottomBar
import ru.zavod.app_navigation.ui.ChatsMenuButton
import ru.zavod.app_navigation.ui.getTitle
import ru.zavod.app_navigation.ui.menuViewed
import ru.zavod.app_navigation.viewmodel.NavigationViewModel

@Composable
fun Navigation(
    viewModel: NavigationViewModel = getViewModel(),
    @DrawableRes chatsButtonIconId: Int,
    onboardingParams: OnboardingParams,
    setConfig: (Configuration) -> Unit
) {
    val navController = rememberNavController()
    Config(viewModel = viewModel, setConfig = setConfig)
    Token(viewModel = viewModel, navController = navController)
    val token by viewModel.tokenStateFlow.collectAsState()
    Params(
        navController = navController,
        viewModel = viewModel,
        chatsButtonIconId = chatsButtonIconId,
        onboardingParams = onboardingParams,
        token = token
    )
}

@Composable
private fun Params(
    navController: NavHostController,
    viewModel: NavigationViewModel,
    @DrawableRes chatsButtonIconId: Int,
    onboardingParams: OnboardingParams,
    token: Token?
) {
    val currentRoute by viewModel.currentRouteStateFlow.collectAsState()
    Content(
        navController = navController,
        currentRoute = currentRoute,
        navigateApi = viewModel.navigateApi,
        chatsButtonIconId = chatsButtonIconId,
        onboardingParams = onboardingParams,
        token = token,
        setCurrentRoute = viewModel::setCurrentRoute
    )
}

@Composable
private fun Content(
    navController: NavHostController,
    currentRoute: String?,
    navigateApi: NavigateApi,
    @DrawableRes chatsButtonIconId: Int,
    onboardingParams: OnboardingParams,
    token: Token?,
    setCurrentRoute: (String?) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (menuViewed(currentRoute = currentRoute)) {
                getTitle(currentRoute = currentRoute)?.let { TopBar(title = it) }
            }
        },
        bottomBar = { BottomBar(currentRoute = currentRoute, navController = navController) },
        floatingActionButton = {
            ChatsMenuButton(
                currentRoute = currentRoute,
                iconId = chatsButtonIconId,
                onClick = { route ->
                    navigateToRoute(
                        navController = navController,
                        itemRoute = route
                    )
                })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.BottomCenter
        ) {
            AppNavHost(
                navController = navController,
                navigateApi = navigateApi,
                onboardingParams = onboardingParams,
                token = token,
                setCurrentRoute = setCurrentRoute
            )
            ShadowDivider()
        }
    }
}

@Composable
private fun ShadowDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = SizeChatsButtonShadowShadowDivider)
            .offset(y = VerticalOffsetChatsButtonShadowShadowDivider)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = COLOR_ALPHA_SHADOW_BOTTOM_BAR
                        )
                    )
                )
            )
    )
}

@Composable
private fun Config(viewModel: NavigationViewModel, setConfig: (Configuration) -> Unit) {
    val config by viewModel.config.collectAsState()
    LaunchedEffect(key1 = config) {
        config?.let { setConfig(it) }
    }
}

@Composable
private fun Token(viewModel: NavigationViewModel, navController: NavHostController) {
    val token by viewModel.tokenStateFlow.collectAsState()
    val authRoute = stringResource(id = R.string.auth_destination)
    LaunchedEffect(key1 = token) {
        if (token == null) {
            navController.navigate(route = authRoute)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
private fun getViewModel(): NavigationViewModel {
    val appContext = LocalContext.current.applicationContext
    val appComponent = (appContext as AppComponentProvider).getAppComponent()
    val viewModelFactory = (appComponent as NavigationComponent).navigationViewModelFactory()
    return viewModel<NavigationViewModel>(factory = viewModelFactory)
}

internal fun navigateToRoute(navController: NavHostController, itemRoute: String) {
    navController.navigate(route = itemRoute) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = false
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}