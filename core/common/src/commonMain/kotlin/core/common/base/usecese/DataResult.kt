package core.common.base.usecese

import core.common.base.model.ProjectException

sealed class DataResult<out R> {
    class Success<out T>(val data: T) : DataResult<T>()
    class Error(val errorBody: ProjectException) : DataResult<Nothing>()
}
