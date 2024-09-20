package core.common.extensions

inline fun <T> T.runIf(condition: Boolean, block: T.() -> T): T = if (condition) {
    block()
} else {
    this
}
