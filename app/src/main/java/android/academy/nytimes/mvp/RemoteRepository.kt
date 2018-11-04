package android.academy.nytimes.mvp

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.utils.Constants
import io.reactivex.Single

interface RemoteRepository {

    fun getNews(category: Constants.Category): Single<List<NewsItem>>

    fun getCategories(): Array<Constants.Category>
}
