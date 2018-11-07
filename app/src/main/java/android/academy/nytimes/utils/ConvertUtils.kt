package android.academy.nytimes.utils

import android.academy.nytimes.exception.ConvertException

@Suppress("NOTHING_TO_INLINE")
inline fun <T> T?.getOrDie(binding: String): T = this
        ?: throw ConvertException("'$binding' must not be null")