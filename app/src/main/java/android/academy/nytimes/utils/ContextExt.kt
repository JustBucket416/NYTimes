package android.academy.nytimes.utils

import android.content.Context
import android.content.res.Configuration

fun Context.isHorizontalOrientation() =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE