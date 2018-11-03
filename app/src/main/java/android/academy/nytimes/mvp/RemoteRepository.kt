package android.academy.nytimes.mvp

import android.academy.nytimes.data.NewsItem
import io.reactivex.Single

interface RemoteRepository {

    fun getNews(category: String): Single<List<NewsItem>>

    fun getCategories(): List<String>
}
