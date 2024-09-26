package core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.common.base.navigation.NavigationState
import core.common.base.usecese.MainEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel<E : ViewEvent, S : ViewState> :
    ViewModel(),
    KoinComponent {

    private val _mainEventsUseCase: MainEventsUseCase by inject()

    private val _viewState = MutableStateFlow(this.getInitialViewState())
    val viewState: StateFlow<S> = _viewState
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = this.getInitialViewState()
        )

    fun navigate(navigationState: NavigationState) {
        viewModelScope.launch {
            _mainEventsUseCase.navigate(navigationState)
        }
    }

    fun navigateBack() {
        navigate(NavigationState.NavigateUp)
    }

    abstract fun handleEvent(event: E)

    /**
     * Initial state of state flow for the ViewState
     */
    abstract fun getInitialViewState(): S

    protected fun setState(newState: S.() -> S) {
        _viewState.update { it.newState() }
    }

    /**
     * Method used for loading the data.
     * This can be triggered in two ways:
     * 1. When a subscriber starts observing the `viewState` flow with `collectAsStateWithLifecycle()`.
     * The timeout is set to `5000L` milliseconds. So if the app goes to the background longer then 5
     * seconds and then it is resumed the `.onStart { loadData() }` will be called thus loading the data.
     * 2. Manually from the viewModel
     * */
    protected open fun loadData() {
    }
}
