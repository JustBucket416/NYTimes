package android.academy.nytimes.utils

class ConvertException(message: String? = null) : IllegalArgumentException(message)

@Suppress("NOTHING_TO_INLINE")
inline fun <T> T?.getOrDie(binding: String): T = this
        ?: throw ConvertException("'$binding' must not be null")