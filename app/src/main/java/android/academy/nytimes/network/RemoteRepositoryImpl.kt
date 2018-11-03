package android.academy.nytimes.network

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.mvp.RemoteRepository
import android.util.Log
import io.reactivex.Single
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val nyTimesApi: NYTimesApi,
                                               private val categories: Map<String, String>) : RemoteRepository {

    companion object {
        private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
        private const val TAG = "remote"
        private const val ERROR_DESCRITION = "unable to parse date"
    }

    override fun getNews(category: String): Single<List<NewsItem>> {
        return nyTimesApi.getNews(categories[category]!!).map { nyResponseRoot ->
            nyResponseRoot.results?.map {
                NewsItem(
                        it.title!!,
                        if (it.multimedia?.isNotEmpty() == true) it.multimedia.get(0).url else "",
                        it.subsection,
                        getDateFromString(it.publishedDate!!.substring(0, it.publishedDate.indexOf('T'))),
                        it.abstract!!,
                        it.url!!
                )
            }
        }
    }

    override fun getCategories(): List<String> {
        return ArrayList(categories.keys)
    }

    private fun getDateFromString(s: String): Date {
        return try {
            DATE_FORMAT.parse(s)
        } catch (e: ParseException) {
            Log.e(TAG, ERROR_DESCRITION)
            Date(Calendar.getInstance().timeInMillis)
        }

    }


}
