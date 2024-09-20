package core.common.base.usecese

import core.common.base.model.MainEvents
import core.common.base.navigation.NavigationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainEventsUseCase : BaseUseCase<MainEvents>() {

    private val _eventsFlow = MutableSharedFlow<DataResult<MainEvents>>()

    suspend fun observeMainEvents(): Flow<DataResult<MainEvents>> = invoke { _eventsFlow.asSharedFlow() }

    suspend fun logoutUser(sessionExpired: Boolean = false) {
        _eventsFlow.emit(DataResult.Success(MainEvents.LogoutUser(sessionExpired)))
    }

    suspend fun navigate(destination: NavigationState) {
        _eventsFlow.emit(DataResult.Success(MainEvents.Navigate(destination)))
    }
}
