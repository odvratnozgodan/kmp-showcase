package core.common.extensions

import core.common.base.usecese.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun <R> DataResult<R>.asFlow(): Flow<DataResult<R>> = flowOf(this)

@OptIn(ExperimentalContracts::class)
inline fun <R, T> DataResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R
): R {
    contract {
        callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success<T> -> onSuccess(this.data as T)
        is DataResult.Error -> onFailure(this.errorBody)
    }
}
