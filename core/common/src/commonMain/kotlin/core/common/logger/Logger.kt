package core.common.logger

import co.touchlab.kermit.Logger

object Logger {

    var tag: String = "TemplateLog"

    fun d(message: String, customTag: String? = null) {
        val tag = customTag ?: tag
        Logger.d(tag = tag, messageString = message)
    }

    fun v(message: String, customTag: String? = null) {
        val tag = customTag ?: tag
        Logger.v(tag = tag, messageString = message)
    }

    fun i(message: String, customTag: String? = null) {
        val tag = customTag ?: tag
        Logger.i(tag = tag, messageString = message)
    }

    fun w(message: String, customTag: String? = null) {
        val tag = customTag ?: tag
        Logger.w(tag = tag, messageString = message)
    }

    fun e(message: String, customTag: String? = null, throwable: Throwable? = null) {
        val tag = customTag ?: tag
        Logger.e(tag = tag, messageString = message, throwable = throwable)
    }

    fun a(message: String, customTag: String? = null) {
        val tag = customTag ?: tag
        Logger.a(tag = tag, messageString = message)
    }
}
