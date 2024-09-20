package feature.home.di

import feature.home.presentation.home.HomeViewModel
import feature.home.presentation.recipe.RecipeDetailsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModuleHome = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::RecipeDetailsViewModel)
}
