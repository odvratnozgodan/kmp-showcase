package core.common.base.usecese

import core.common.base.model.GeneralException
import core.common.base.model.ProjectException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<OUTPUT> {
    protected suspend fun invoke(call: () -> Flow<DataResult<OUTPUT>>): Flow<DataResult<OUTPUT>> = try {
        call.invoke()
    } catch (exception: ProjectException) {
        flow { emit(DataResult.Error(exception)) }
    } catch (exception: Exception) {
        flow {
            emit(
                DataResult.Error(
                    GeneralException.GeneralError(message = exception.message ?: "")
                )
            )
        }
    }
}
