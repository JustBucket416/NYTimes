package android.academy.nytimes.utils

import android.academy.nytimes.data.CategorizedNewsItem
import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.data.UncategorizedNewsItem
import android.academy.nytimes.network.model.Result
import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewsItemConverter @Inject constructor() {

    companion object {
        @SuppressLint("SimpleDateFormat")
        private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
        private const val TAG = "data parse"
    }

    fun convertToNewsItem(result: Result): NewsItem =
            with(result) {
                val dateString = result.publishedDate.getOrDie("date")
                if (result.subsection == null) {
                    return@with UncategorizedNewsItem(
                            title = result.title.getOrDie("title"),
                            imageUrl = result.multimedia?.getOrNull(0)?.url,
                            publishDate = getDateFromString(dateString),
                            previewText = result.abstract.getOrDie("preview text"),
                            url = result.url.getOrDie("url")
                    )
                }
                return@with CategorizedNewsItem(
                        title = result.title.getOrDie("title"),
                        imageUrl = result.multimedia?.getOrNull(0)?.url,
                        publishDate = getDateFromString(dateString),
                        previewText = result.abstract.getOrDie("preview text"),
                        url = result.url.getOrDie("url"),
                        category = result.subsection.getOrDie("category")
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
}

