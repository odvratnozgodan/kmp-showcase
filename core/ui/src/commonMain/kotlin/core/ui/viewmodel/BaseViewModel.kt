package core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.common.base.navigation.NavigationState
import core.common.base.usecese.MainEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
     * Method used for refreshing the data. It should be called when the Fragment.onResume() is called
     * */
    open fun refreshData() {
    }
}
