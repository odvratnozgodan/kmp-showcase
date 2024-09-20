package core.datastore.di

import okio.FileSystem
import org.koin.dsl.module

private const val USER_PREFERENCES = "settings"

actual val coreModuleDatastorePlatformSpecific = module {

    single<FileSystem> {
        FileSystem.SYSTEM
    }
}
