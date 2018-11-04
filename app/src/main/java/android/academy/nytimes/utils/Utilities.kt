package android.academy.nytimes.utils

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.network.model.Result
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
private const val TAG = "data parse"

fun String.Companion.empty() = ""

fun Context.isHorizontalOrientation() =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Result.convertToNewsItem(): NewsItem {
    if (title == null || publishedDate == null || abstract == null || url == null) throw IllegalArgumentException("Bad server response")
    return NewsItem(
            title,
            if (multimedia?.isNotEmpty() == true) multimedia[0].url else String.empty(),
            subsection,
            getDateFromString(publishedDate.substring(0, publishedDate.indexOf('T'))),
            abstract,
            url
    )
}

private fun getDateFromString(s: String): Date {
    return try {
        DATE_FORMAT.parse(s)
    } catch (e: ParseException) {
        Log.w(TAG, e.message)
        Date(Calendar.getInstance().timeInMillis)
    }

}