package core.common.di

import core.common.base.usecese.MainEventsUseCase
import org.koin.dsl.module

val coreModuleCommon = module {
    single { MainEventsUseCase() }
}
