package recipes.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import recipes.data.remote.datasource.RecipesDataSource
import recipes.data.remote.datasource.RecipesDataSourceImpl
import recipes.data.remote.service.RecipesService
import recipes.data.repository.repo.RecipesRepository
import recipes.data.repository.repo.RecipesRepositoryImpl
import recipes.domain.usecase.RecipesUseCase

val coreModuleRecipes = module {
    single { RecipesUseCase(get()) }

    single<RecipesDataSource> {
        RecipesDataSourceImpl(
            get(qualifier = named("authenticatedHttpClient")),
            get(),
            get()
        )
    }

    single<RecipesRepository> { RecipesRepositoryImpl(get()) }

    single<RecipesService> {
        RecipesService()
    }
}
