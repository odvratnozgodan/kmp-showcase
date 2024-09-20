package core.datastore.di

import com.russhwolf.settings.Settings
import core.datastore.data.repository.TokenRepository
import core.datastore.data.repository.TokenRepositoryImpl
import core.datastore.domain.usecase.GetAccessToken
import core.datastore.domain.usecase.GetRefreshToken
import core.datastore.domain.usecase.SetAccessToken
import core.datastore.domain.usecase.SetRefreshToken
import core.datastore.store.DataStoreManager
import core.datastore.store.DataStoreManagerImpl
import org.koin.core.module.Module
import org.koin.dsl.module

private const val USER_PREFERENCES = "settings"

val coreModuleDatastore = module {
    includes(coreModuleDatastorePlatformSpecific)

    single { Settings() }
    single<DataStoreManager> { DataStoreManagerImpl(get()) }
    single<TokenRepository> { TokenRepositoryImpl(get()) }

    // Use cases
    factory { GetRefreshToken(get()) }
    factory { SetRefreshToken(get()) }
    factory { GetAccessToken(get(), get()) }
    factory { SetAccessToken(get()) }
}

expect val coreModuleDatastorePlatformSpecific: Module
