package android.academy.nytimes.mvp

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.utils.Category
import io.reactivex.Single

interface RemoteRepository {

    fun getNews(category: Category): Single<List<NewsItem>>

    fun getCategories(): Array<Category>
}
