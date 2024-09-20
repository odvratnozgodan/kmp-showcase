package core.common.extensions

import core.common.base.usecese.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip

fun <T, R> Flow<DataResult<T>>.doOnResultSuccess(transform: suspend (value: DataResult.Success<T>) -> DataResult<R>): Flow<DataResult<T>> =
    onEach {
        when (it) {
            is DataResult.Error -> flowOf(it)
            is DataResult.Success -> transform(it)
        }
    }

@OptIn(ExperimentalCoroutinesApi::class)
fun <T, R> Flow<DataResult<T>>.transformOnResultSuccess(
    transform: suspend (value: DataResult.Success<T>) -> DataResult<R>,
): Flow<DataResult<R>> = flatMapMerge {
    when (it) {
        is DataResult.Error -> flowOf(it)
        is DataResult.Success -> flowOf(transform(it))
    }
}

fun <T1, T2, R> Flow<DataResult<T1>>.zipOnResultSuccess(
    flow: Flow<DataResult<T2>>,
    transform: suspend (a: DataResult.Success<T1>, b: DataResult.Success<T2>) -> DataResult<R>,
): Flow<DataResult<R>> = this.zip(flow) { first, second ->
    when (first) {
        is DataResult.Error -> first
        is DataResult.Success -> {
            when (second) {
                is DataResult.Error -> second
                is DataResult.Success -> {
                    transform(first, second)
                }
            }
        }
    }
}
