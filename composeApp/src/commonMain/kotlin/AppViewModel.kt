import androidx.lifecycle.viewModelScope
import core.common.base.model.MainEvents
import core.common.base.navigation.NavigationState
import core.common.base.navigation.ScreenNavigationRoute
import core.common.base.usecese.MainEventsUseCase
import core.common.extensions.collectDataResult
import core.datastore.domain.usecase.GetAccessToken
import core.ui.viewmodel.BaseViewModel
import core.user.domain.usecase.LogoutUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(
    private val mainEventsUseCase: MainEventsUseCase,
    private val getAccessToken: GetAccessToken,
    private val logoutUser: LogoutUser,
) : BaseViewModel<AppEvent, AppViewState>() {
    private val _navigation = MutableSharedFlow<NavigationState>()
    val navigation: SharedFlow<NavigationState> = _navigation

    private val _isInitialDataReady = MutableStateFlow(false)
    val isInitialDataReady: StateFlow<Boolean> = _isInitialDataReady.asStateFlow()

    private val _initialNavigationDestination = MutableStateFlow<ScreenNavigationRoute>(ScreenNavigationRoute.NoAction)
    val initialNavigationDestination: StateFlow<ScreenNavigationRoute> =
        _initialNavigationDestination.asStateFlow()

    init {
        viewModelScope.launch {
            observeMainEvents()
            checkInitialNavigationDestination()
            delay(750)
            _isInitialDataReady.value = true
        }
    }

    private fun checkInitialNavigationDestination() {
        viewModelScope.launch {
            when {
                isUserLoggedIn() -> _initialNavigationDestination.emit(ScreenNavigationRoute.Home)
                else -> _initialNavigationDestination.emit(ScreenNavigationRoute.Login())
            }
        }
    }

    override fun handleEvent(event: AppEvent) {
    }

    private fun observeMainEvents() {
        viewModelScope.launch {
            mainEventsUseCase.observeMainEvents().collectDataResult(
                onSuccess = {
                    when (it) {
                        is MainEvents.LogoutUser -> logoutUserFromApp(it.sessionExpired)
                        is MainEvents.Navigate -> _navigation.emit(it.destination)
                    }
                },
                onError = {
                    println("error:${it.errorBody}")
                }
            )
        }
    }

    private fun logoutUserFromApp(sessionExpired: Boolean) {
        viewModelScope.launch {
            logoutUser()
            navigate(
                NavigationState.NavigateToDestination(
                    destination = ScreenNavigationRoute.Login(),
                    clearBackStack = true
                )
            )
        }
    }

    private suspend fun isUserLoggedIn(): Boolean = withContext(viewModelScope.coroutineContext) {
        val accessTokenResult = getAccessToken()
        accessTokenResult?.let {
            accessTokenResult.isNotBlank()
        } ?: run {
            false
        }
    }

    override fun getInitialViewState() = AppViewState()
}
