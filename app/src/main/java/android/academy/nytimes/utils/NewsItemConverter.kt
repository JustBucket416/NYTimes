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
                val dateString = publishedDate.getOrDie("date")
                val item = if (subsection == null) {
                    UncategorizedNewsItem(
                            title = title.getOrDie("title"),
                            imageUrl = multimedia?.getOrNull(0)?.url,
                            publishDate = getDateFromString(dateString),
                            previewText = abstract.getOrDie("preview text"),
                            url = url.getOrDie("url")
                    )
                } else {
                    CategorizedNewsItem(
                            title = title.getOrDie("title"),
                            imageUrl = multimedia?.getOrNull(0)?.url,
                            publishDate = getDateFromString(dateString),
                            previewText = abstract.getOrDie("preview text"),
                            url = url.getOrDie("url"),
                            category = subsection.getOrDie("category")
                    )
                }
                item
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

