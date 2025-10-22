package core.ui.viewmodel

import androidx.lifecycle.viewModelScope
import core.ui.viewmodel.state.StateMachine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

abstract class BaseStateViewModel<E : ViewEvent, S : State, VS : ViewState> : BaseViewModel<E, VS>() {


    abstract val stateMachine: StateMachine<S, E>

    override val viewState: StateFlow<VS> by lazy {
        stateMachine.state.map { state ->
            mapState(state)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = getInitialViewState(),
        )
    }

    abstract fun mapState(state: S): VS

    override fun handleEvent(event: E) {
        stateMachine.onEvent(event)
    }
}
