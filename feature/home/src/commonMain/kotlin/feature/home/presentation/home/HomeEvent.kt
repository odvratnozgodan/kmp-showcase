package feature.home.presentation.home

import core.ui.viewmodel.ViewEvent
import recipes.data.remote.model.Recipe

sealed class HomeEvent : ViewEvent {
    data class OnItemClicked(val recipe: Recipe) : HomeEvent()
}
