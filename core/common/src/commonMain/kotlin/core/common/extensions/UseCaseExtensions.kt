package core.common.extensions

import core.common.base.usecese.DataResult
import kotlinx.coroutines.flow.Flow

suspend inline fun <OUTPUT> Flow<DataResult<OUTPUT>>.collectDataResult(
    crossinline onSuccess: suspend (response: OUTPUT) -> Unit,
    crossinline onError: suspend (response: DataResult.Error) -> Unit,
) {
    this.collect {
        when (it) {
            is DataResult.Error -> {
                onError(it)
            }

            is DataResult.Success -> {
                onSuccess(it.data)
            }
        }
    }
}
