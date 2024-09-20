package core.common.extensions

fun String?.ifNullOrBlank(defaultValue: () -> String): String = if (isNullOrBlank()) defaultValue() else this

fun String.ifBlank(defaultValue: () -> String?): String? = if (isBlank()) defaultValue() else this

fun String?.ifNotNullOrBlank(defaultValue: (String) -> String): String = if (isNullOrBlank()) "" else defaultValue(this)

fun String?.firstOr(defaultValue: () -> String): String = if (isNullOrBlank()) defaultValue() else this.first().toString()
