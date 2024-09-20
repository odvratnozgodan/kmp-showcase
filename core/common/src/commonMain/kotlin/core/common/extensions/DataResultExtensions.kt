package core.common.extensions

import core.common.base.usecese.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun <R> DataResult<R>.asFlow(): Flow<DataResult<R>> = flowOf(this)
